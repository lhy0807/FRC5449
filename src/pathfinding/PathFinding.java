package pathfinding;

import java.util.ArrayList;
import java.util.Collections;

/**patched*/
public class PathFinding {
	//system enum
	protected enum pathFindStatus{
		Route_NOT_Available,
		Route_Found,
		Route_Processing
	}
	
	//map system - (ableNode_Value = 0)
	private static double scalingConstant;
	private static final int clearNode_Value = 0;
	private static final int blockedNode_Value = 1;
	private static final int closeNode_Value = 2;
	private static final int safetyNode_Value = 3; 
	private static int MapXLength;
	private static int MapYLength;
	private static int[][] CourtMap; //court map size in centimeters 
	private final int RobotXRadius;
	private final int RobotYRadius;
	private final int RobotSafetyLength;
	
	//path finding system
	private static ArrayList<MapNode> openList;
	private static ArrayList<MapNode> closedList;
	private static ArrayList<MapNode> traceList;
	private static int[][] GMap;
	private static int[][] HMap;
	private static int[][] ParentXMap;
	private static int[][] ParentYMap;
	private MapNode startNode;
	private MapNode goalNode;
	private MapNode currNode;
	private boolean goalReached = false; //patch for o.equals() un-overrode-able
	
	/**Constructor
	 * @param x1 Starting
	 * @param useChebyshev use Chebyshev to calculate Exact Distance rather than Manhattan*/
	public PathFinding(int x1, int y1, int x2, int y2, boolean useChebyshev, int safetyDistance, double FASTER){
		
		scalingConstant = FASTER;
		MapXLength = (int)(180/scalingConstant);
		MapYLength = (int)(240/scalingConstant);
		CourtMap = new int[MapYLength][MapXLength]; //court map size in centimeters 
		RobotXRadius = (int)(22.5/scalingConstant);
		RobotYRadius = (int)(22.5/scalingConstant);
		
		
		//initiate lists
		openList = new ArrayList<MapNode>();
		closedList = new ArrayList<MapNode>();
		traceList = new ArrayList<MapNode>();
		GMap = new int[MapYLength][MapXLength];
		HMap = new int[MapYLength][MapXLength];
		ParentXMap = new int[MapYLength][MapXLength];
		ParentYMap = new int[MapYLength][MapXLength];
		
		//initiate Map System
		RobotSafetyLength = (int)(safetyDistance/scalingConstant);
		for(int i = 0;i<CourtMap.length;i++){
			for(int j=0;j<CourtMap[i].length;j++){
				CourtMap[i][j]=0;
			}
		}
		
		//initiate situations
		for(int i = 0;i<GMap.length;i++){//y
			for(int j=0;j<GMap[i].length;j++){//x
				GMap[i][j]=0;
				ParentXMap[i][j]=0;
				ParentYMap[i][j]=0;
				if(useChebyshev){
					int dx = Math.abs(x2-j);
					int dy = Math.abs(y2-i);
					HMap[i][j]=MapNode.cost1*(Math.max(dx, dy)-Math.min(dx, dy))+MapNode.cost2*Math.min(dx, dy);
				}else{
					HMap[i][j]=Math.abs(x2-j)*MapNode.cost1 + Math.abs(y2-i)*MapNode.cost1;
				}
			}
		}
		startNode = new MapNode((int)(x1/scalingConstant), (int)(y1/scalingConstant));
		goalNode = new MapNode((int)(x2/scalingConstant), (int)(y2/scalingConstant));
		//load map in here
		loadFieldLayout();
	}
	
	/**must call this or you will get nothing*/
	public void findPath(ArrayList<Integer> waypointListX, ArrayList<Integer> waypointListY, ArrayList<Integer> waypointDirection, boolean emptyLists,ArrayList<Integer> waypointShortListX,ArrayList<Integer> waypointShortListY){
		//Initiate
		openList.add(startNode);
		startNode.setParent(startNode);
		startNode.setG(0);
		//find
		do{
			currNode = findMinF(openList);
			closeMapNode(currNode);
			for(MapNode forNode : currNode.getAdjacent()){
				boolean tempContain = false;
				for(MapNode ContainCheckNode:openList){
					if(ContainCheckNode.equals(forNode)){
						tempContain = true;
						break;
					}
				}
				if(!tempContain){//.contains() doesn't work! See below
					openList.add(forNode);
					forNode.setParent(currNode);
					if(forNode.getG()==0){
						forNode.setG(currNode.getG()+forNode.getMovementCost(currNode.getX(),currNode.getY()));
					}
				}else{
					int tempNewF = currNode.getG()+forNode.getMovementCost(currNode.getX(),currNode.getY());
					if(forNode.getG()>tempNewF){
						forNode.setG(tempNewF);
						forNode.setParent(currNode);
					}
				}
			}
		}while(isEnd().equals(PathFinding.pathFindStatus.Route_Processing));
		//tracing raw points
		if(!goalReached){
			System.out.println("[Warning]:Goal Can NOT be Reached! \t");
		}else{
			MapNode tempNode = goalNode;
			traceList.add(tempNode);
			while((!tempNode.equals(startNode))){
				tempNode = new MapNode(tempNode.getParentX(), tempNode.getParentY());
				traceList.add(tempNode);
			}
			Collections.reverse(traceList);
		}
		
		//tracing turn points and making outputs
		if((!waypointListX.isEmpty()||!waypointListY.isEmpty()||!waypointDirection.isEmpty())&&emptyLists){
			waypointListX.clear();
			waypointListY.clear();
			waypointDirection.clear();
		}
		if(traceList.equals(null)||traceList.size()==0){
			System.out.println("[Warning]:traceList UNREACHABLE! ");
		}else{
			int iTurn = getXYAngle(traceList.get(1).getX()-traceList.get(0).getX(), traceList.get(1).getY()-traceList.get(0).getY());
			waypointListX.add((int) (scalingConstant*traceList.get(0).getX()));
			waypointListY.add((int) (scalingConstant*traceList.get(0).getY()));
			waypointShortListX.add((int) (scalingConstant*traceList.get(0).getX()));
			waypointShortListY.add((int) (scalingConstant*traceList.get(0).getY()));
			waypointDirection.add(iTurn);
			for(int i=1;i<traceList.size()-1;i++){
				int dx=traceList.get(i+1).getX()-traceList.get(i).getX();
				int dy=traceList.get(i+1).getY()-traceList.get(i).getY();
				if(getXYAngle(dx, dy)!=iTurn){
					
					waypointShortListX.add((int) (scalingConstant*traceList.get(i).getX()));
					waypointShortListY.add((int) (scalingConstant*traceList.get(i).getY()));
					
				}
				iTurn = getXYAngle(dx, dy);
				waypointListX.add((int) (scalingConstant*traceList.get(i).getX()));
				waypointListY.add((int) (scalingConstant*traceList.get(i).getY()));
				waypointDirection.add(iTurn);
			}//map end point
			waypointListX.add((int) (scalingConstant*traceList.get(traceList.size()-1).getX()));
			waypointListY.add((int) (scalingConstant*traceList.get(traceList.size()-1).getY()));
			waypointShortListX.add((int) (scalingConstant*traceList.get(traceList.size()-1).getX()));
			waypointShortListY.add((int) (scalingConstant*traceList.get(traceList.size()-1).getY()));
			
			waypointDirection.add(0);
		}
	}
	
	
	private void loadFieldLayout(){
		//init
		setRectObstacle(0, 0, MapXLength-5, MapYLength-5, clearNode_Value);//right
		//set required blocked
		setRectObstacle((int)(106/scalingConstant-RobotXRadius), (int)(5/scalingConstant), (int)(134/scalingConstant+RobotXRadius), (int)(118/scalingConstant+RobotYRadius), blockedNode_Value); //middle
		setRectObstacle((int)(1/scalingConstant), (int)(114/scalingConstant-RobotYRadius), (int)(60/scalingConstant+RobotXRadius), (int)(126/scalingConstant+RobotYRadius), blockedNode_Value);//far side
		setRectObstacle((int)(143/scalingConstant-RobotXRadius), (int)(145/scalingConstant-RobotYRadius), (int)(161/scalingConstant), (int)(163/scalingConstant+RobotYRadius), blockedNode_Value);//near side
		//set start & end safety override
		setObstacleOverride(startNode.getX(), startNode.getY());
		setObstacleOverride(goalNode.getX(), goalNode.getY());
	}
	
	/**xStart and xEnd and as well as yStart and yEnd are inclusive*/
	private void setRectObstacle(int xStart, int yStart, int xEnd, int yEnd, int value){
		for(int i=yStart;i<=yEnd;i++){ //y
			for(int j=xStart;j<=xEnd;j++){ //x
				CourtMap[i][j] = value;
			}
		}
	}
	/**NOT TESTED*/
	private void setObstacleOverride(int x, int y){//TODO test this
		for(int i=(y-RobotYRadius>0)?(y-RobotYRadius):0;i<((y+RobotYRadius<MapYLength)?(y+RobotYRadius):MapYLength);i++){ //y
			for(int j=(x-RobotXRadius>0)?(x-RobotXRadius):0;j<((x+RobotXRadius<MapXLength)?(x+RobotXRadius):MapXLength);j++){ //x
				if(CourtMap[i][j]==safetyNode_Value){
					CourtMap[i][j] = clearNode_Value;
				}/*else if(CourtMap[i][j]==closeNode_Value){
					//config output
					System.out.println("Something is Going WRONG! - "+x+" "+y);
				}*/
				
				
			}
		}
	}
	
	//Path Finding System Functions
	/**<Strong>
	 * Warning:
	 * <p>.contains(goalNode) Will ALWAYS return FALSE! </Strong>
	 * <p>(because it uses the .equals() method that was not Overrode in MapNode.java)
	 * */
	protected pathFindStatus isEnd(){
		if(goalReached){
			return pathFindStatus.Route_Found;
		}else if(openList.isEmpty()){
			return pathFindStatus.Route_NOT_Available;
		}else {
			return pathFindStatus.Route_Processing;
		}
	}
	/**@return angle value*/
	private int getXYAngle(int dx,int dy){
		if(dx==1&&dy==1){
			return 45;
		}else if(dx==1&&dy==0){
			return 90;
		}else if(dx==1&&dy==-1){
			return 135;
		}else if(dx==0&&dy==1){
			return 0;
		}else if(dx==0&&dy==-1){
			return 180;
		}else if(dx==-1&&dy==1){
			return -45;
		}else if(dx==-1&&dy==0){
			return -90;
		}else if(dx==-1&&dy==-1){
			return -135;
		}else{
			return Integer.parseInt("wtf");
		}
	}
	/**find smallest F*/
	private MapNode findMinF(ArrayList<MapNode> openList){
		if(openList!=null){
			MapNode temp = openList.get(0);
			for(MapNode checkNode : openList){
				if(checkNode.getF()<=temp.getF()){
					temp = checkNode;
				}else{}
			}
			return temp;
		}else{
			return null;
		}
	}
	/**close node*/
	private void closeMapNode(MapNode mapNode){
		openList.remove(mapNode);
		mapNode.closeNode();
		if(mapNode.equals(goalNode)){
			goalReached = true;
			goalNode = currNode;
		}
		closedList.add(mapNode);
	}
	
	//MapNode Class supporting A* Path finding
	/**This class is now public for testing, will be put to private afterwards*/
	public class MapNode {
		public static final int cost1 = 10;
		public static final int cost2 = 14;
		private final int x;
		private final int y;
		public int G;
		public int H;
		
		public MapNode(int x, int y) {
			this.x = x;
			this.y = y;
			this.G = PathFinding.GMap[y][x];
			this.H = PathFinding.HMap[y][x];
		}
		
		public int getMovementCost(int xDesti, int yDesti){
			if(Math.abs(xDesti-x)==1&&Math.abs(yDesti-y)==1){
				return cost2;
			}else if(Math.abs(xDesti-x)==1||Math.abs(yDesti-y)==1){
				return cost1;
			}else{
				System.out.println("#MAPNODE ERROR on"+x+"-"+xDesti+"_"+y+"-"+yDesti+"#");
				return 0;
			}
		}
		
		public ArrayList<MapNode> getAdjacent(){
			ArrayList<MapNode> AdjacentSet = new ArrayList<MapNode>();
			for(int j=y-1;j<=y+1;j++){ //y
				for(int i=x-1;i<=x+1;i++){ //x
					
					if((i==x&&j==y)||
							(j<0)||(i<0)||
							(j>=MapYLength)||
							(i>=MapXLength)||
							CourtMap[j][i]==closeNode_Value||
							CourtMap[j][i]==blockedNode_Value||
							CourtMap[j][i]==safetyNode_Value){
						
					}else{
						AdjacentSet.add(new MapNode(i, j));
					}
				}
			}
			return AdjacentSet;
		}
		
		public void closeNode(){
			CourtMap[y][x] = closeNode_Value;
		}
		
		//@Override
		public boolean equals(MapNode node) {
	        return this.x==node.getX()&&this.y==node.getY();
	    }
		
		public void setG(int G) {
			this.G = G;
			PathFinding.GMap[y][x] = G;
		}
		
		public int getG(){
			return PathFinding.GMap[y][x];
		}
		
		public int getH(){
			return PathFinding.HMap[y][x];
		}
		
		public int getF(){
			return PathFinding.GMap[y][x]+PathFinding.HMap[y][x];
		}
		
		public void setParent(MapNode parent) {
			//this.parent = parent;
			PathFinding.ParentXMap[y][x] = parent.getX();
			PathFinding.ParentYMap[y][x] = parent.getY();
		}
		
		public int getParentX() {
			return PathFinding.ParentXMap[y][x];
		}
		
		public int getParentY() {
			return PathFinding.ParentYMap[y][x];
		}
		
		public int getX(){
			return x;
		}
		
		public int getY(){
			return y;
		}
		
		public int getValue(){
			return CourtMap[y][x];
		}
	}
}
