; Riley Moore
; 04-21-21
; Assignment #7
;CS 2350 - 002
; This program takes a floating point input as a diameter and calculates the radius, circumfrence
;	and surface area of a circle
;Methodology:
;I made a floating point variable to store every valure needed for all three equations to call as i need
;Then i just load and store the vallues as needed for the certian equations

INCLUDE Irvine32.inc
INCLUDE macros.inc
.data
	;Memory operands
	inputV REAL8 ?
	radiusV REAL8 ?
	circV REAL8  ?
	surfV REAL8 ?
	twoV REAL8 2.0
	piV REAL8 ?
	squaredV REAL8 ?
.code
main PROC
	mWrite "Riley Moore		R11651701"	;Calling name and Rnum to console
	call crlf
;-----------------------------------------------------------------------
	finit					;initalize the FPU register
	;fld inputV
	mWrite "Enter the diameter offset your circle: "
	call ReadFloat			;Reading in the floatin point value
	call crlf
	fstp inputV				;Store the diameter
;-----------------------------------------------------------------------
;Performing Radius calculations
	fld inputV				;load diameter and value 2
	fld twoV				; Radius = Diameter/2
	fdiv					;ST(0) = inputV / twoV
	mWrite "Radius: "
	call WriteFloat			;Display the radius
	call crlf
	fstp radiusV			;Store radius in operand
;-----------------------------------------------------------------------
;Performing Circumfrence calculations
	fld radiusV
	fldpi		;ST(0) = pi, ST(1) = twov
	fld twoV
	fmul ST(0), ST(1)	;Pi * 2
	fmul ST(0), ST(2)	;(pPi * 2) * Radius
	mWrite "Circumfrence: "
	call WriteFloat		;Dispay Circumfrence
	call crlf
	fstp circV
	fstp piV			;Clear the stack
	fstp radiusV		;Store values in corresponding operands
;-----------------------------------------------------------------------
;Performing Surface Area calculations
	fld radiusV
	fld radiusV			;Load the value of Radius twice for r^2
	fmul
	fldpi				;Load pi for r^2 * pi
	fmul
	mWrite "Surface Area: "
	call WriteFloat		;Display Surface area to user
	exit
main ENDP
END main
