;Name: Riley Moore (R11651701)		Date: 04-20-21		Assignment: 5

;This program takes a user input string and key, then xor's the two
;Acts as a encrpyt/decrpyt program

;1.)Set up main driver code for user interaction
;2.)Move input and key position and move them into different registers
;3.)Compare each register to see if null or need to be reset
;4.)Once evaluated, xor and move into destination string
;5.)Add null  terminator, point to the start of destination, and call procedure
	;---------------------------------------------------------------

INCLUDE Irvine32.inc

.data
	inputString BYTE 500 DUP(0)		;input, key, and result memory operands
	keyString BYTE 20 DUP(0)
	newString BYTE 500 DUP(0)

	prompt BYTE "Enter a message to encyrpt: ", 0	;Prompt strings to display
	keyPrompt BYTE "Enter your key: ", 0
	resultPrompt BYTE "Result: ", 0
	nameR BYTE "Riley Moore     R11651701", 0

.code
main PROC
	;---------------------------------------------------------------
	mov edx, OFFSET nameR		;Print name and Rnumber
	call WriteString
	call crlf
	call crlf
	;---------------------------------------------------------------

	;---------------------------------------------------------------
	mov edx, OFFSET prompt		;Print for the user input
	call WriteString
	mov edx, OFFSET inputString	;Read adn store user input
	mov ecx, SIZEOF inputString
	call ReadString
	call crlf
	;---------------------------------------------------------------

	;---------------------------------------------------------------
	mov edx, OFFSET keyPrompt		;Print Key prompt
	call WriteString
	mov edx, OFFSET keyString		;Read in the users input
	mov ecx, SIZEOF keyString
	call ReadString
	call crlf
	;---------------------------------------------------------------

	;---------------------------------------------------------------
	;Code block for calling str_xor
	mov edi, OFFSET newString		;Point EDI, ESI, EDX to point to the correct strings
	mov esi, OFFSET inputString
	mov edx, OFFSET keyString
	call str_xor
	mov edx, OFFSET resultPrompt	;Display result prompt
	call WriteString
	mov edx, edi
	call WriteString				;Display newString form xoring
	call crlf
	;---------------------------------------------------------------

	exit
main ENDP

;-------------------------------------------------------------------
;Procedure to xor a string for encryption/decryption
;Recieves: ESI (input String), EDX (Key String), EDI (new String or destination stirng)
;Returns: the newstring pointing at edi
;Requires: Knoweldge of where the strings at being pointed at
str_xor PROC

mov ebp, 0
L1:mov al, [esi]		;Loop to move input s0tring
	cmp al, 00h			;Compare to null
	JE finished			;Done if yes
	JNE L2				;Need to xor if not

L2: mov bl, [edx]		;Move key into register
	cmp bl, 00h			;Compare to null
	JE R1				;Reset pointer if so
	JNE L3				;Continue to xor loop is not

R1: mov edx, OFFSET keyString
	jmp L2

L3: xor al, bl			;xor al(input) and bl(key)
	mov [edi], al		;Move into destination string
	inc esi				;Move to the next postion
	inc edx
	inc edi
	jmp L1

finished:
	mov al, 00h					;Creating the null-terminated string
	mov [edi], al
	mov edi, OFFSET newString	;Point the beginning of the newString


ret
str_xor ENDP
;--------------------------------------------------------------------
END main
