using FluentValidation;
using MediatR;
using Microsoft.AspNetCore.Http;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Options;
using Pcca.Core.Config;
using Pcca.Data.Persistence;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using CsvHelper;
using System.Globalization;
using CsvHelper.Configuration.Attributes;
using System;
using Pcca.Data.Models.GinEquityMaster;

namespace GinTools.Core.Features.OtherAllocations;

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
        public string TaxId { get; set; }
        [CsvHelper.Configuration.Attributes.Index(5)]
        public string ItemClass { get; set; }
        [CsvHelper.Configuration.Attributes.Index(6)]
        public string PendingItemClassAmount { get; set; }
        [CsvHelper.Configuration.Attributes.Index(7)]
        public string ItemClassAmount { get; set; }
        [CsvHelper.Configuration.Attributes.Index(8)]
        public string PendingItemClassQuantity { get; set; }
        [CsvHelper.Configuration.Attributes.Index(9)]
        public string ItemClassQuantity { get; set; }
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
        private readonly GinEquityDbContext db;
        private readonly AppConfig appConfig;
        public CommandHandler(GinEquityDbContext db, IOptions<AppConfig> appConfig)
        {
            this.db = db;
            this.appConfig = appConfig.Value;
        }

        public async Task<List<Allocation>> Handle(Command request, CancellationToken token)
        {
            /****************************************
             *                                      *
             *          CSV HELPER                  *
             *                                      *
            ****************************************/

            //Create stream to read in the file 
            //Default Path: C:\Users\r.moore\Documents\PCCA\pcca\GinTools\GinTools
            var stream = request.File.OpenReadStream();
            var reader = new StreamReader(stream);

            //Declare properties
            var csv = new CsvReader(reader, CultureInfo.InvariantCulture);
            var result = new List<Allocation>();

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
                            AccountNo = short.Parse(record.AccountNo),
                            GinCode = int.Parse(record.GinCode),
                            LotNo = byte.Parse(record.LotNo),
                            TaxId = record.TaxId,
                            ItemClass = record.ItemClass,
                            PendingItemClassAmount = decimal.Parse(record.PendingItemClassAmount),
                            ItemClassAmount = decimal.Parse(record.ItemClassAmount),
                            PendingItemClassQuantity = decimal.Parse(record.PendingItemClassQuantity),
                            ItemClassQuantity = decimal.Parse(record.ItemClassQuantity),
                        };

                        result.Add(temp);
                    }
                }
            }

            /****************************************
             *                                      *
             *             Original                 *
             *                                      *
            ****************************************/
            //First approach of reading in the csv data by hand

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
            //    while(index < rawValues.Count)
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

            //    //Validate incoming data
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

            //    string taxId = lineValues[4];
            //    if (taxId == null)
            //        allocation.Message.Add("Invlaid Tax Id");

            //    string itemClass = lineValues[5];
            //    if (itemClass == null)
            //        allocation.Message.Add("Invlaid Item Class");

            //    decimal pendingAmount = 0;
            //    decimal.TryParse(lineValues[6], out pendingAmount);
            //    if (!(pendingAmount > 0))
            //        allocation.Message.Add("Invalid Pending Item Class Amount");

            //    decimal amount = 0;
            //    decimal.TryParse(lineValues[7], out amount);
            //    if (!(amount > 0))
            //        allocation.Message.Add("Invalid Item Class Amount");

            //    decimal pendingQuantity = 0;
            //    decimal.TryParse(lineValues[7], out pendingQuantity);
            //    if (!(pendingQuantity > 0))
            //        allocation.Message.Add("Invalid Pending Item Class Quantity");

            //    decimal quantity = 0;
            //    decimal.TryParse(lineValues[7], out quantity);
            //    if (!(quantity > 0))
            //        allocation.Message.Add("Invalid Item Class Quantity");

            //    //Assign properties to allocation
            //    allocation.CropYear = cropYear;
            //    allocation.GinCode = ginCode;
            //    allocation.AccountNo = accountNo;
            //    allocation.LotNo = lotNo;
            //    allocation.TaxId = taxId;
            //    allocation.ItemClass = itemClass;
            //    allocation.PendingItemClassAmount = pendingAmount;
            //    allocation.ItemClassAmount = amount;
            //    allocation.PendingItemClassQuantity = pendingQuantity;
            //    allocation.ItemClassQuantity = quantity;

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
                    && a.TaxId == record.TaxId
                    && a.ItemClass == record.ItemClass) > 1)
                {
                    record.Message.Add($"Duplicate reecord at key(s): Crop Year = {record.CropYear}" +
                        $"Gin Code = {record.GinCode}, Account No = {record.AccountNo}," +
                        $"Lot No = {record.LotNo}, Tax Id = {record.TaxId}," +
                        $"Item Class = {record.ItemClass}");
                }

                //Remove duplicates in memory logic
                var table = await db.OtherAllocations
                    .AnyAsync(a => a.CropYear == record.CropYear
                        && a.GinCode == record.GinCode
                        && a.AccountNo == record.AccountNo
                        && a.LotNo == record.LotNo
                        && a.TaxId == record.TaxId
                        && a.ItemClass == record.ItemClass, token);

                //Add message if duplicate
                if (table)
                    record.Message.Add("Record already exisits in the table: Crop Year = {record.CropYear}" +
                        $"Gin Code = {record.GinCode}, Account No = {record.AccountNo}," +
                        $"Lot No = {record.LotNo}, Tax Id = {record.TaxId}," +
                        $"Item Class = {record.ItemClass} at line {result.IndexOf(record) + 2}");
            }
            return result;
        }
    }
}
