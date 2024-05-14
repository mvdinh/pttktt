package NQueens;

public class NQueen
{
    public static void main(String args[]){
        int N = 8;
        AllSolNQueen nQueen = new AllSolNQueen(N);
        System.out.println("Total of result: " + nQueen.getResults());
        nQueen.printAllSolutions();
    }
}
