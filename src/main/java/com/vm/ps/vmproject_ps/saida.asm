; Calcula a média de 2 números e armazena em R0
ADD R0, R1
ADD R0, R2
DIV R0, 2


          ; Chamada à macro MACRO1
          ADD R0, R3
          ADD R0, R4
          DIV R0, 2


          ; Imprime o resultado
          PRINT    R0

; Calcula a média de 3 números e armazena em R0
ADD R0, R1
ADD R0, R2
ADD R0, R3
DIV R0, 3


          ; Chamada à macro MACRO2
          ADD R0, R4
          ADD R0, R5
          ADD R0, R6
          DIV R0, 3


          ; Imprime o resultado
          PRINT    R0

; Calcula o quadrado de 1 numero e armazena em R0        
MOV R0, R1
MUL R0, R0
MUL R0, R0


;Calcula o cubo de 1 numero e armazena em R0
MOV R0, R1
MUL R0, R0
MUL R0, R0
MUL R0, R0

;Calcula a Potencialização de 1 numero e armazena em R0
MOV R0, 1
MOV R1, R1
POTENCIA:
    CMP R1, 0
    JE FIM_POTENCIA
    MUL R0, R1
    DEC R1
    JMP POTENCIA
FIM_POTENCIA:

;Calcula a fatorial e armazena em R0
MOV R0, 1
MOV R1, R1
CMP R1, 1
JZ fim_macro6
inicio_macro6:
    MUL R0, R1
    DEC R1
    CMP R1, 1
    JNZ inicio_macro6
fim_macro6: MOV R0, R1

         
