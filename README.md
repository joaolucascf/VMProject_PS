# VMProject_PS
Virtual machine implementation project, proposed as a final project for approval in the Systems Programming discipline, taught by Professor Dr. Anderson Ferrugem
Computador hipotético feito em java

# 1 - Memória
  * tamanho de pelo menos 1kb
  * palavra de memória de 24 bits (3 bytes)
  * unidade de endereçamento: palavra

  # 2 - Registradores

  * 0- A - acumulador de 24bits armazena dados das operações da ULA

  * 1- X - registrador de índice de 24bits, usado para endereçamento

  * 2- L - registrador de ligação de 24bits, a instrução "Jump to Subroutine" (JSUB) armazena o endereço de retorno nesse registrador

  * 3- B - registrador base de 24 bits, usado para endereçamento

  * 4- S - registrador de uso geral de 24 bits

  * 5- T - registrador de uso geral de 24 bits

  * 6- F - acumulador de ponto flutuante de 48 bits, armazena os dados carregados e resultantes das operações da ULA em ponto flutuante

  * 8- PC - contador de instruções de 24bits, mantém o endereço da próxima instrução a ser executada

  * 9- SW - palavra de status, 24bits, contém várias informações, incluindo código condicional (CC)

  # 3 - Modos de endereçamento
  
  * Direto - valor do operando está no endereço de destino, determinado diretamento usando as informações de deslocamento/endereço
  * Indireto - valor do operando está no endereço de destino, armazenado no endereço fornecido pelo cálculo do endereço direto
  * Imediato - valor do operando é o valor do cálculo do endereço direto (endereço de destino é o registro de instrução na CPU)
  
  # 4 - Conjunto de instruções (o que o programa faz de fato)
  
  P = privilegiado, C = conjunto CC(<, =, >), F = ponto flutuante, "m" indica posição de memória (A <- A + m..m+2 implica que A é adicionado do conteúdo das memórias m
  a m+2). "formato" indica qual o formato de instrução que deve ser usado(3/4 indica 3 ou 4)

  * ADD: operando(m), formato(3/4), opcode(18), ação(A <- (A)+(m..m+2))
  * ADDR: operando(r1, r2), formato(2), opcode(90), ação(r2 <- r2+r1)
  * AND: operando(m), formato(3/4), opcode(40), ação()
  * CLEAR: operando(r1), formato(2), opcode(4), ação()
  * COMP: operando(m), formato(3/4), opcode(28), ação(), tipo(C)
  * COMPR: operando(r1,r2), formato(2), opcode(A0), ação(), tipo(C)
  * DIV: operando(m), formato(3/4), opcode(24), ação()
  * DIVR: operando(r1,r2), formato(2), opcode(9C), ação()
  * J: operando(m), formato(3/4), opcode(3C), ação()
  * JEQ: operando(m), formato(3/4), opcode(30), ação()
  * JGT: operando(m), formato(3/4), opcode(34), ação()
  * JLT: operando(m), formato(3/4), opcode(38), ação()
  * JSUB: operando(m), formato(3/4), opcode(48), ação()
  * LDA: operando(m), formato(3/4), opcode(0), ação()
  * LDB: operando(m), formato(3/4), opcode(68), ação()
  * LDCH: operando(m), formato(3/4), opcode(50), ação()
  * LDL: operando(m), formato(3/4), opcode(8), ação()
  * LDS: operando(m), formato(3/4), opcode(6C), ação()
  * LDT: operando(m), formato(3/4), opcode(74), ação()
  * LDX: operando(m), formato(3/4), opcode(4), ação()
  * MUL: operando(m), formato(3/4), opcode(20), ação()
  * MULR: operando(r1,r2), formato(2), opcode(98), ação()
  * OR: operando(m), formato(3/4), opcode(44), ação()
  * RMO: operando(r1,r2), formato(2), opcode(AC), ação()
  * RSUB: operando(-), formato(3/4), opcode(4C), ação()
  * SHIFTL: operando(r1,n), formato(2), opcode(A4), ação()
  * SHIFTR: operando(-), formato(2), opcode(A8), ação()
  * STA: operando(m), formato(3/4), opcode(0C), ação()
  * STB: operando(m), formato(3/4), opcode(78), ação()
  * STCH: operando(m), formato(3/4), opcode(54), ação()
  * STL: operando(m), formato(3/4), opcode(14), ação()
  * STS: operando(m), formato(3/4), opcode(7C), ação()
  * STT: operando(m), formato(3/4), opcode(84), ação()
  * STX: operando(m), formato(3/4), opcode(10), ação()
  * SUB: operando(m), formato(3/4), opcode(1C), ação()
  * SUBR: operando(r1, r2), formato(2), opcode(94), ação()
  * TIX: operando(m), formato(3/4), opcode(2C), ação(), tipo(C)
  * TIXR: operando(r1), formato(2), opcode(B8), ação(), tipo(C)
  
  Formato de instruções:

 * 1 byte: op(8bits)
 * 2 bytes: r1(8bits), r2(8bits)
 * 3 bytes: op(6bits), n i x b p e(1 bit cada), disp (12 bits)
 * 4 bytes: op(6bits), n i x b p e(1 bit cada), disp (20 bits)
