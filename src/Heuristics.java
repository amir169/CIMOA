/**
 * Created by Amir Shams on 11/29/2015.
 */
public class Heuristics
{
    int[][] worldMatrix;
    int[][] LRTAStarmatrix;
    double[][] darknessMatrix;
    double[][] lastSeenMatrix;
    int LRTAStarINF;

    public void setWorldMatrix(int[][] worldMatrix) {
        this.worldMatrix = worldMatrix;
    }

    public Heuristics(int[][] worldMatrix,int LRTAStarINF) {
        this.LRTAStarINF=LRTAStarINF;
        this.worldMatrix = worldMatrix;
        LRTAStarmatrix=new int[worldMatrix.length][worldMatrix[0].length];
        initialLRTAStarMatrx();
        initialDarknessMatrix();
        initialLastSeenMatrix();

    }

    //must call in constructor
    void initialLRTAStarMatrx(){
        for (int i = 0; i < worldMatrix.length; i++) {
            for (int j = 0; j < worldMatrix[0].length; j++) {
                if(worldMatrix[i][j]!=0)
                    LRTAStarmatrix[i][j]=LRTAStarINF;
                else
                    LRTAStarmatrix[i][j]= 0-LRTAStarINF;
            }
        }

    }

    void initialDarknessMatrix()
    {
        darknessMatrix = new double[worldMatrix.length][worldMatrix[0].length];
        for(int i=0;i<worldMatrix.length;i++)
            for(int j=0;j<worldMatrix[0].length;j++)
                darknessMatrix[i][j] = 0.0;
    }

    void initialLastSeenMatrix()
    {
        lastSeenMatrix = new double[worldMatrix.length][worldMatrix[0].length];
        for(int i=0;i<worldMatrix.length;i++)
            for(int j=0;j<worldMatrix[0].length;j++)
                lastSeenMatrix[i][j] = 1;
    }
}
