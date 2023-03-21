public class Memory {
    final int line = 343;
    final int column = 3;
    final byte[][] mem = new byte [line][column];
    final byte space = 1;
    final byte[] word = new byte [2];

    void memAlloc(byte[][] mem){
        int i, j;
        for(i = 0 ; i < line ; i++){
            
            for(j = 0 ; j < column ; j++){
                mem[i][j] = 0;
            }
        }
    }
    
    byte[] getWord(int i){
        int j, k = 0;
        for(j = 0 ; j < 2 ; j++){
            this.word[k] = mem[i][j];
            k++;
        }
        return word;
    }
}
