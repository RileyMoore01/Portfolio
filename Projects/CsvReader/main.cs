using CsvHelper;
using FluentValidation;
using MediatR;
using Microsoft.AspNetCore.Http;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Options;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;

namespace hiddenForPrivacyReasons;

public class Upload
{
    public class Command : IRequest<List<Allocation>>
    {
        public IFormFile File { get; set; }
    }

    public class Allocation : Add.Allocation
    {
        public List<string> Message { get; set; }
    }

    public class CsvData
    {
        [CsvHelper.Configuration.Attributes.Index(0)]
        public string CropYear { get; set; }

        [CsvHelper.Configuration.Attributes.Index(1)]
        public string GinCode { get; set; }

        [CsvHelper.Configuration.Attributes.Index(2)]
        public string AccountNo { get; set; }

        [CsvHelper.Configuration.Attributes.Index(3)]
        public string LotNo { get; set; }

        [CsvHelper.Configuration.Attributes.Index(4)]
        public string CompanyNo { get; set; }

        [CsvHelper.Configuration.Attributes.Index(5)]
        public string DividendTaxId { get; set; }

        [CsvHelper.Configuration.Attributes.Index(6)]
        public string DividendPercentage { get; set; }
    }

    public class CommandValidator : AbstractValidator<Command>
    {
        public CommandValidator()
        {
            RuleFor(c => c.File)
                .NotEmpty()
                    .WithMessage("Please select a file")
                .Must(c => c.FileName.EndsWith(".csv"))
                    .WithMessage("test");

            //Custom Validation for csv
            RuleFor(c => c.File).Custom((list, context) => {
                if (list.FileName.EndsWith(".csv"))
                    context.AddFailure("The file must be a csv extension");
            });
        }
    }

    public class CommandHandler : IRequestHandler<Command, List<Allocation>>
    {
        private readonly hiddenForPrivacyReasons db;

        public CommandHandler(hiddenForPrivacyReasons db) => this.db = db;

        public async Task<List<Allocation>> Handle(Command request, CancellationToken token)
        {
            /****************************************
            *                                      *
            *          CSV HELPER                  *
            *                                      *
           ****************************************/

            //Create stream to read in the file 
            var stream = request.File.OpenReadStream();
            var reader = new StreamReader(stream);

            //Declare Properties
            var csv = new CsvReader(reader, CultureInfo.InvariantCulture);
            var result = new List<Allocation>();

            try
            {
                //CSV Validation
                if (request.File.FileName.EndsWith(".csv"))
                {
                    //Read in the stream data using csvHelper library
                    //CsvData is the all essential properties of an allocation
                    //Indexing has to be in the same order that the helper will read it in
                    var data = csv.GetRecords<CsvData>();
                    result = new List<Allocation>();

                    //Convert the read-in data to the Allocation class with non-essential properties
                    foreach (var record in data)
                    {
                        if (record.CropYear != "")
                        {
                            var temp = new Allocation
                            {
                                CropYear = short.Parse(record.CropYear),
                                GinCode = int.Parse(record.GinCode),
                                AccountNo = short.Parse(record.AccountNo),
                                LotNo = byte.Parse(record.LotNo),
                                CompanyNo = byte.Parse(record.CompanyNo),
                                DividendTaxId = record.DividendTaxId,
                                DividendPercentage= decimal.Parse(record.DividendPercentage),
                            };

                            result.Add(temp);
                        }
                    }
                }
            }

            catch(Exception ex)
            {
                //Some user feedback here
            }
            /****************************************
            *                                      *
            *             Original                 *
            *                                      *
           ****************************************/

            //var stream = request.File.OpenReadStream();
            //var fileName = request.File.FileName;
            //stream.Seek(0, SeekOrigin.Begin);

            //var allocations = new List<Allocation>();
            ////var reader = new StreamReader(stream);
            //var headers = reader.ReadLine();    //Remove headers from stream
            //while (reader.Peek() >= 0)
            //{
            //    //Read in the csv data seperated by commas
            //    var allocation = new Allocation();
            //    allocation.Message = new List<string>();
            //    string line = reader.ReadLine();

            //    //Check if the file is EOF
            //    if (line.Contains(",,,"))
            //        continue;

            //    List<string> rawValues = line.Split(',').ToList();
            //    var lineValues = new List<string>();


            //    //Format & Validate comma seperation
            //    int index = 0;
            //    while (index < rawValues.Count)
            //    {
            //        var temp = "";
            //        if (rawValues[index].Contains("\\") || rawValues[index].Contains('"'))
            //        {
            //            temp = rawValues[index] + rawValues[index + 1];
            //            temp = temp.Replace("\\", "");
            //            temp = temp.Replace("\"", "");
            //            lineValues.Add(temp);
            //            index++;
            //        }
            //        else
            //            lineValues.Add(rawValues[index]);
            //        index++;
            //    }

            //    //Read in each property seperatley and check they are valid
            //    short cropYear = 0;
            //    short.TryParse(lineValues[0], out cropYear);
            //    if (cropYear < 0 && cropYear > 3000)
            //        allocation.Message.Add("Invalid Crop Year");

            //    int ginCode = 0;
            //    int.TryParse(lineValues[1], out ginCode);
            //    if (!(ginCode > 0))
            //        allocation.Message.Add("Invalid Gin Code");

            //    short accountNo = 0;
            //    short.TryParse(lineValues[2], out accountNo);
            //    if (!(accountNo > 0))
            //        allocation.Message.Add("Invalid Account No");

            //    byte lotNo = 0;
            //    byte.TryParse(lineValues[3], out lotNo);
            //    if (!(lotNo >= 0))
            //        allocation.Message.Add("Invalid Lot No");

            //    byte companyNo = 0;
            //    byte.TryParse(lineValues[4], out companyNo);
            //    if (!(companyNo > 0))
            //        allocation.Message.Add("Invlaid Account No");

            //    string dividendTaxId = lineValues[5];
            //    if (dividendTaxId == null)
            //        allocation.Message.Add("Invlaid Dividend Tax Id");

            //    decimal dividendPercentage = 0;
            //    decimal.TryParse(lineValues[6], out dividendPercentage);
            //    if (!(dividendPercentage >= 0))
            //        allocation.Message.Add("Invalid Percentage");

            //    //Store seperated properties in the current instance of Allocation
            //    allocation.CropYear = cropYear;
            //    allocation.GinCode = ginCode;
            //    allocation.AccountNo = accountNo;
            //    allocation.LotNo = lotNo;
            //    allocation.CompanyNo = companyNo;
            //    allocation.DividendTaxId = dividendTaxId;
            //    allocation.DividendPercentage = dividendPercentage;

            //    //Add the current instance of allocation to allocations list
            //    allocations.Add(allocation);
            //}

            /****************************************
            *                                      *
            *          Post Data-Call              *
            *                                      *
            ****************************************/
            foreach (var record in result)
            {
                record.Message = new List<string>();

                //Remove duplicates in allocations logic
                if (result.Count(a => a.CropYear == record.CropYear
                    && a.GinCode == record.GinCode
                    && a.AccountNo == record.AccountNo
                    && a.LotNo == record.LotNo
                    && a.CompanyNo == record.CompanyNo
                    && a.DividendTaxId == record.DividendTaxId) > 1)
                {
                    record.Message.Add($"Duplicate record at key(s): Crop Year = {record.CropYear}" +
                        $"Gin Code = {record.GinCode}, Account No = {record.AccountNo}," +
                        $"Lot No = {record.LotNo}, Company No = {record.CompanyNo}," +
                        $"Tax Id = {record.DividendTaxId}");
                }

                //Remove duplicates in memory logic
                var table = await db.GinDividends
                    .AnyAsync(a => a.CropYear == record.CropYear
                        && a.GinCode == record.GinCode
                        && a.AccountNo == record.AccountNo
                        && a.LotNo == record.LotNo
                        && a.CompanyNo == record.CompanyNo
                        && a.DividendTaxId == record.DividendTaxId
                        , token);

                //Add message if duplicate
                if (table)
                    record.Message.Add($"Record already exisits in the table:" +
                        $"Crop Year = {record.CropYear}" +
                        $"Gin Code = {record.GinCode}, Account No = {record.AccountNo}," +
                        $"Lot No = {record.LotNo}, Company No = {record.CompanyNo}," +
                        $"Tax Id = {record.DividendTaxId} at line {result.IndexOf(record) + 2}");
            }

            return result;
        }
    }
}
