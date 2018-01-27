package potential_field;

public class Square_obstacle extends Obstacle{
	
	private double Angle;
	private double Width;
	private double Length;
	private double K_exp = this.K_expell;
	private double range = this.Range;
	private double[] Coordinates;
	private boolean is_encountered = false;
	private double[] t_p = {0,0};
	private boolean CCW = true;
	
	public Square_obstacle(double[] Coordinates ,double Length ,double Width ,double Angle){
		this.Length = Length;
		this.Width = Width;
		this.Angle = Angle;//counter clockwise
		this.Coordinates = Coordinates;
		is_encountered = false;
		CCW = true;
	}


	@Override
	public double[] expell_force(double[] Position,double[] VectorToGoal) {
	
		Dot_obstacle obs;
		double[] nx = {1,0};//Unit vector in X direction
		double[] ny = {0,1};//Unit vector in Y direction
		
		double[] V_RG = {0,0};//Robot -> Goal
		V_RG[0] = VectorToGoal[0];
		V_RG[1] = VectorToGoal[1];
		double p_RG = Math.hypot(V_RG[0], V_RG[1]);//distance between robot and goal
		
		double[] p = {Coordinates[0] - Position[0],Coordinates[1] - Position[1]};//relative position vector 
		double p_mag = Math.hypot(p[0], p[1]);
		
		double[] n_p = {0,0};
		n_p[0] = p[0] / p_mag;
		n_p[1] = p[1] / p_mag;
		
		
		
		
		
		double px = 0,py = 0;//the position vector in X and Y direction
		
		px = Dot(p,nx);
		py = Dot(p,ny);
		
		double gx = Dot(V_RG,nx);
		double gy = Dot(V_RG,ny);
		
		double[] ans = {0,0};
		
		
		boolean T_force = true;
		boolean In = false;
				
		
		if (Math.abs(px) <= this.Width/2){
			if (Math.abs(py) <= this.Length/2){
				return ans;//Inside the box, return 0
			}else{	
				double[] coord = {-px + Coordinates[0],-Math.signum(py)*Length/2+Coordinates[1]};
				In = new Dot_obstacle(coord,Range).is_In(Position, VectorToGoal);
				if (!is_encountered){
					is_encountered = In;
					if (is_encountered){
						CCW = Decide(px,py,gx,gy)[0];
						T_force = Decide(px,py,gx,gy)[1];

					}
				}
				
				ans = new Dot_obstacle(coord,Range).expell_force(Position, VectorToGoal);
			}
		} else{ 
			if (Math.abs(py) <= this.Length/2){
				double[] coord = {-Math.signum(px)*Width/2 + Coordinates[0],-py + Coordinates[1]};
				In = new Dot_obstacle(coord,Range).is_In(Position, VectorToGoal);
				if (!is_encountered){
					is_encountered = In;
					if (is_encountered){
						CCW = Decide(px,py,gx,gy)[0];
						T_force = Decide(px,py,gx,gy)[1];

					}
				}
				
				
				ans = new Dot_obstacle(coord,Range).expell_force(Position, VectorToGoal);
			}else{
				double[] pos = {0,0};
				pos[0] = -Math.signum(px) * Width/2;
				pos[1] = -Math.signum(py) * Length/2;
				pos = Rotate(pos,Angle);
				pos[0] += Coordinates[0];
				pos[1] += Coordinates[1];
				ans = new Dot_obstacle(pos,Range).expell_force(Position,VectorToGoal);
				In = new Dot_obstacle(pos,Range).is_In(Position, VectorToGoal);
				if (!is_encountered){
					is_encountered = new Dot_obstacle(pos,Range).is_In(Position, VectorToGoal);
					if (is_encountered){
						CCW = Decide(px,py,gx,gy)[0];
						T_force = Decide(px,py,gx,gy)[1];

					}
				}	
				
			}
		}
		if (is_encountered&&In&&T_force){

			if (CCW){
				t_p = Rotate(n_p,Math.PI/2);
			}else{
				t_p = Rotate(n_p,-Math.PI/2);
			}
			ans[0] += range(-t_p[0] * p_RG*1,-10,10);
			ans[1] += range(-t_p[1] * p_RG*1,-10,10);
		}
		return ans;
	}
	
	private boolean[] Decide(double px,double py,double gx,double gy){
		boolean px2,py2,gx2,gy2;
		px2 = !(px>=0);
		py2 = !(py>=0);
		gx2 = (gx>=0);
		gy2 = (gy>=0);
		boolean[] ans = {true,true};
		if (px2){
			if (py2){
				if(gx2){
					if(gy2){
						//NO Force
						ans[1] = false; 
					}else{
						//CW
						ans[0] = false;
						ans[1] = true;
					}
				}else{
					if(gy2){
						//CCW
						ans[0] = true;
						ans[1] = true;
					}else{
						//TODO
						ans[0] = true;
						ans[1] = true;
					}
				}
			}else{
				if(gx2){
					if(gy2){
						//CCW
						ans[0] = true;
						ans[1] = true;
					}else{
						//NO Force
						ans[1] = false;
					}
				}else{
					if(gy2){
						//TODO
						ans[0] = true;
						ans[1] = true;
					}else{
						//CW
						ans[0] = false;
						ans[1] = true;
					}
				}
			}
		}else{
			if(py2){
				if(gx2){
					if(gy2){
						//CW
						ans[0] = false;
						ans[1] = true;
					}else{
						//TODO
						ans[0] = false;
						ans[1] = true;
					}
				}else{
					if(gy2){
						//NO Force
						ans[1] = false;
					}else{
						//CCW
						ans[0] = true;
						ans[1] = true;
					}
				}
			}else{
				if(gx2){
					if(gy2){
						//TODO
						ans[0] = false;
						ans[1] = true;
					}else{
						//CCW
						ans[0] = true;
						ans[1] = true;
					}
				}else{
					if(gy2){
						//CW
						ans[0] = false;
						ans[1] = true;
					}else{
						//NO Force
						ans[1] = false;
					}
				}
			}
		}
		
		return ans;
	}
	
	
	public double[] Rotate(double[] Vector,double Radius){
		//Positive angle means rotating counterclockwise
		//angle in radius
		//tested
		double[] ans = {0,0};
		ans[0] = Math.cos(Radius) * Vector[0] - Math.sin(Radius) * Vector[1];
		ans[1] = Math.sin(Radius) * Vector[0] + Math.cos(Radius) * Vector[1];
		return ans;
	}
	
	public double Dot(double[] Vector1,double[] Vector2){
		return Vector1[0] * Vector2[0] + Vector1[1] * Vector2[1];
	}
	
	 private double range(double val,double min,double max){
	    	if (val < min){
	    		return min;
	    	}else if (val > max){
	    		return max;
	    	}else{
	    		return val;
	    	}
	    } 
	
}
