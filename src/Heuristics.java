/**
 * Created by Amir Shams on 11/29/2015.
 */
public class Heuristics
{
    int[][] worldMatrix;
    int[][] LRTAStarmatrix;
    int LRTAStarINF;

    public void setWorldMatrix(int[][] worldMatrix) {
        this.worldMatrix = worldMatrix;
    }

    public Heuristics(int[][] worldMatrix,int LRTAStarINF) {
        this.LRTAStarINF=LRTAStarINF;
        this.worldMatrix = worldMatrix;
        LRTAStarmatrix=new int[worldMatrix.length][worldMatrix[0].length];
        initialLRTAStarMatrx();
    }

    //must call in constructor
    void initialLRTAStarMatrx(){
      //  printuMat();
        System.out.println();
        for (int i = 0; i < worldMatrix.length; i++) {
            for (int j = 0; j < worldMatrix[0].length; j++) {
                if(worldMatrix[i][j]!=0)
                    LRTAStarmatrix[i][j]=LRTAStarINF;
                else
                    LRTAStarmatrix[i][j]= 0-LRTAStarINF;
            }
        }
    }
    private void printuMat() {
        int m[][]=worldMatrix;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                System.out.print(m[j][i]+" ");
            }
            System.out.println();
        }
    }
}
