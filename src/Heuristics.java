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
        initialLRTAStarMatrx();
    }

    //must call in constructor
    void initialLRTAStarMatrx(){
        LRTAStarmatrix=new int[worldMatrix.length+2][worldMatrix[0].length+2];
        for (int i = 0; i < worldMatrix.length+2; i++) {
            for (int j = 0; j < worldMatrix[0].length+2; j++) {
                if(worldMatrix[i][j]!=0 || i==0 || i==worldMatrix.length +1 || j==0 || j==worldMatrix[0].length+1)LRTAStarmatrix[i][j]=LRTAStarINF;
                else LRTAStarmatrix[i][j]= 0-LRTAStarINF;
            }
        }
    }
}
