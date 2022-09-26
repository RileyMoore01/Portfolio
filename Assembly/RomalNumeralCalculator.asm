; Riley Moore
; 04-21-21
; Assignment #6
; This program converts Roman Numerals to Arabic numerals using integer conversion.
; I check the unquie roman numerals {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1}
;Once value is found, find corresponding symbol {"I","IV","V","IX","X","XL","L","XC","C","CD","D","CM","M"}
;Then send that symbol to edi, subtract that value from esi, repeat until esi = 0
;Once finsihed add null terminator, return to main, repeat until user input 0 is reached or user terminated

INCLUDE Irvine32.inc
.data
	;Memory operands
	inputAr QWORD ?			;input quadword
	resultR QWORD ?			;result quadword
	TempNum DWORD ?			;temp for tracking/debuging
	;Prompt operands
	inputPrompt BYTE "Enter a positive Arabic Numeral(0 to quit): ", 0
	resultPrompt BYTE "Your Arabic Numeral in Roman Numeral form is: ", 0
	ErrorPrompt BYTE "Try again, No negative integers aloud ", 0
	endPrompt BYTE "Program is finished!", 0
	nameR BYTE "Riley Moore		R11651701", 0
.code
main PROC
;-----------------------------------------------------------------------
	mov edx, OFFSET nameR	;Print Name and Rnum
	call WriteString
	call crlf
;-----------------------------------------------------------------------
UserInput:	mov edx, OFFSET inputPrompt		;Loop to repeat procedure until found 0
	call WriteString				;ask for user data
	mov edx, OFFSET inputAr			;read in and store the value
	mov ecx, SIZEOF inputAr
	call Readstring
	mov eax, [edx]
	cmp eax, 30h						;Checking for zero
	mov [edx], al
	JB errorLoop					;If a negitive integer, call eroor
	JE EndProgram					;Ending program if zero
	JA callLoop2					;If a positive integer, call proc

errorLoop: mov edx, OFFSET ErrorPrompt	;Loop incase of negative input data
	call WriteString
	call crlf
	jmp UserInput						;once error message is read, jump to restart


callLoop2:
	mov edx, OFFSET inputAr
	INVOKE Str_copy, ADDR [edx], ADDR TempNum
	mov esi, TempNum
	mov edi, OFFSET resultR
	call convert					;calling the function
	mov edx, OFFSET resultPrompt	;Showing the user the result
	call WriteString
	mov edx, OFFSET resultR			;Output the roman numerals to display
	call Writestring
	call crlf
	call crlf
	jmp UserInput
EndProgram: mov edx, OFFSET endPrompt
	call WriteString
	call crlf

	exit
main ENDP
;-----------------------------------------------------------------------
;Requires:
;Returns: EDI pointing to the new string converted to Roman Numerals
;Recieves: EDX pointing to the input string in Arabic Numeral form
convert PROC
;-----------------------------------------------------------------------
Down_List:				;Loop to find the correct value for the correct symbol
	cmp esi, 30303031h
	JAE L1000			;Jump to corresponding L loop to convert to roman numerals into edi and subtract the value needed
	cmp esi, 30303030h	;Check the end of the thousandths place
	JE finished
	cmp esi, 00303039h	;Value: 900
	JAE L900
	cmp esi, 00303035h	;Value:	500
	JAE L500
	cmp esi, 00303034h	;Value:400
	JAE L400
	cmp esi, 00303031h	;Value: 100
	JAE L100
	cmp esi, 00303030h	;To check the end of the hundreths place
	JE finished
	cmp esi, 00003039h	;Value: 90
	JAE L90
	cmp esi, 00003035h	;Value: 50
	JAE L50
	cmp esi, 00003034h  ;Value: 40
	JAE L40
	cmp esi, 00003031h	;Value: 10
	JAE L10
	cmp esi, 00003030h	;Check the end of the tens place
	JE finished
	cmp esi, 00000039h	;Value: 9
	JAE L9
	cmp esi, 00000035h	;Value: 5
	JAE L5
	cmp esi, 00000034h	;Value: 4
	JAE L4
	cmp esi, 00000031h	;Value: 1
	JAE L1
	cmp esi, 00000030h	;Check for the end of ones place
	jmp finished		;Otherwise finsh

	cmp esi, 000000000h
	jmp finished

L1000: sub esi, 1h		;Subtract the valued base number from the current standing number
	mov al, 4Dh			;Move roman numeral into EAX register
	mov [edi], al		;Move EAX into positioned edi value
	inc edi				;Move to the next position of edi
	jmp Down_List		;Jump up to repeat until given number, or esi,  is 0

L900: sub esi, 303039h
	mov al, 43h
	mov [edi], al
	inc edi
	mov al, 4dh
	mov [edi], al
	inc edi
	jmp Down_List

L500:sub esi, 5h
	mov al, 44h
	mov [edi], al
	inc edi
	jmp Down_List

L400:sub esi, 303034h
	mov al, 43h
	mov [edi], al
	inc edi
	mov al, 44h
	mov [edi], al
	inc edi
	jmp Down_List

L100:sub esi, 1h
	mov al, 43h
	mov [edi], al
	inc edi
	jmp Down_List

L90: sub esi, 9h
	mov al, 58h
	mov [edi], al
	inc edi
	mov al, 43h
	mov [edi], al
	inc edi
	jmp Down_List

L50:sub esi, 5
	mov al, 4Ch
	mov [edi], al
	inc edi
	jmp Down_List

L40:sub esi, 4h
	mov al, 58h
	mov [edi], al
	inc edi
	mov al, 4Ch
	mov [edi], al
	inc edi
	jmp Down_List

L10: sub esi, 1h
	mov al, 58h
	mov [edi], al
	inc edi
	jmp Down_List

L9:sub esi, 9h
	mov al, 49h
	mov [edi], al
	inc edi
	mov al, 58h
	mov [edi], al
	inc edi
	jmp Down_List

L5:sub esi, 5h
	mov al, 56h
	mov [edi], al
	inc edi
	jmp Down_List

L4:sub esi, 4h
	mov al, 49h
	mov [edi], al
	inc edi
	mov al, 56h
	mov [edi], al
	inc edi
	jmp Down_List

L1:sub esi, 1h
	mov al, 49h
	mov [edi], al
	inc edi
	jmp Down_List

finished: mov al, 00h			;Once fully repeated adn value 0, add a null to the end of edi
	mov [edi], al
	mov edi, OFFSET resultR		;Set edi to the beginning of OFFSET resultR to be read to display
ret								;Return to main to repeat and check for rules again
convert ENDP
END main
