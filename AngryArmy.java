import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.*;
public class AngryArmy extends Applet implements MouseListener,MouseMotionListener 
{
	Image img,wind,play1,play2,health,cana,canb,endi,ball,ball1,chance,winner,lose;
	MediaTracker tr;
	int p[][]=new int[2][2];
	int c=0,r,dx,dy,pa,pb,dist,a1=12,b1=12,w,apos,bpos;
	boolean m=false,p1=false,player=true,dir=true,la=false,lb=false,end=false,busy=false,game_over=false;
	float t,d;
	String s="";
	Color co=new Color(238,243,250);
	Random win=new Random(5);
	Random a=new Random();
	Random b=new Random();
	public void init() 
	{
		addMouseListener(this);
		addMouseMotionListener(this);
		tr = new MediaTracker(this);
		winner= getImage(getCodeBase(), "winner.PNG");
		lose= getImage(getCodeBase(), "loser.PNG");
		ball= getImage(getCodeBase(), "ball.PNG");
		ball1= getImage(getCodeBase(), "ball1.PNG");
		endi= getImage(getCodeBase(), "end.PNG");
		cana= getImage(getCodeBase(), "canona.PNG");
		canb= getImage(getCodeBase(), "canonb.PNG");
		img = getImage(getCodeBase(), "back.PNG");
		wind =getImage(getCodeBase(), "wind.PNG");
		play1 =getImage(getCodeBase(), "player1.PNG");
		play2 =getImage(getCodeBase(), "player2.PNG");
		health =getImage(getCodeBase(), "health.PNG");
		chance =getImage(getCodeBase(), "chance.PNG");
		tr.addImage(winner,0);
		tr.addImage(lose,0);
		tr.addImage(img,0);
		tr.addImage(ball,0);	
		tr.addImage(ball1,0);
		tr.addImage(wind,0);		
		tr.addImage(play1,0);
		tr.addImage(play2,0);
		tr.addImage(cana,0);
		tr.addImage(canb,0);
		tr.addImage(endi,0);
		tr.addImage(health,0);
		tr.addImage(chance,0);
		while(dist<80)
		{
			apos=Math.abs(a.nextInt(500));
			bpos=Math.abs(b.nextInt(500));
			pa=(int)(10+apos+60);
			pb=(int)(1014-bpos-60);
			dist=pb-pa-40;
		}
	}
	public void mouseClicked(MouseEvent  me)
	{
		if(!busy)
		{
			if(!(a1<=0||b1<=0))
			{
				if(c%2==0)
				{
					p[0][0]=me.getX();
					p[0][1]=me.getY();
					m=true;
				}
				if(c%2==1)
				{
					p[1][0]=me.getX();
					p[1][1]=me.getY();
					m=false;
					p1=true;
					player=!player;
					repaint();
					if(r%2==0)
					{
					w=win.nextInt(25);
					}
				else
				{
					w=win.nextInt(20)*(-1);
				}
				}
				c++;
			}
			else
			{
				game_over=true;
			}
		}
	}
	public void mouseEntered(MouseEvent  me){}
	public void mouseExited (MouseEvent  me){}
	public void mousePressed(MouseEvent  me){}
	public void mouseReleased(MouseEvent me){}
	public void mouseDragged(MouseEvent  me){}
	public void mouseMoved(MouseEvent  me) 
	{
		p[1][0]=me.getX();
		p[1][1]=me.getY();
		dx=p[1][0]-p[0][0];
		dy=p[1][1]-p[0][1];
		if(player)
		{
			if(dx<=0)
			{
				s="";
				dir=true;
			}
			else
			{
				s="-";
				dir=false;
			}
		}
		else
		{
			if(dx<=0)
			{
				s="-";
				dir=false;
			}
			else
			{
				s="";
				dir=true;
			}
		}
		repaint();
	}
	public void paint(Graphics  g) 
	{
		update(g);
	}
	public void update(Graphics g)
	{	        
		if(game_over)
		{
			if(a1<=0)
			{
				g.drawImage(winner,555,630,this);
				g.drawImage(lose,150,630,this);		
			}
			if(b1<=0)
			{
				g.drawImage(lose,555,630,this);
				g.drawImage(winner,150,630,this);
			}	
		}
		else
		{
			g.drawImage(health,144,17,this);
			g.setColor(co);
			g.fillRect(144+a1*25,17,300-a1*25,32);
			g.drawImage(health,705,17,this);
			g.setColor(co);
			g.fillRect(705+b1*25,17,300-b1*25,32);
			g.setColor(Color.BLACK);
			g.drawRect(18,16,426,33);
			g.drawRect(458,16,108,33);
			g.drawRect(579,16,426,33);
			g.setColor(Color.WHITE);
			g.fillRect(459,39,52,7);
			g.fillRect(514,39,52,7);
			g.setColor(Color.BLACK);
			//g.drawString("Player A: "+(pa)+" Player B: "+(pb)+" RANGE : "+r+" DISTANCE : "+dist+" A :"+a1+" B :"+b1+" WIND: "+w,500,200);
			if(w<0)
			{
				for(int q=0;q<Math.abs(w);q++)
				g.fillRect(508-q*2,40,q*2,6);
				//g.drawString("minus",100,300);
			}
			else
			{
				for(int l=0;l<Math.abs(w);l++)
				g.fillRect(516,40,l*2,6);
				//g.drawString("plus",100,300);
			}
			g.drawImage(img,0,0,this);
			g.drawImage(cana,pa,510,this);
			g.drawImage(canb,pb,510,this);
			g.drawImage(wind,459,17,this);
			g.drawImage(play1,19,17, this);
			g.drawImage(play2,580,17,this);
			if(!player&&!game_over)
			{
				g.drawImage(chance,555,630,this);
			}
			if(player&&!game_over)
			{
				g.drawImage(chance,150,630,this);		
			}	
			if(m)
			{
				g.drawLine(p[0][0],p[0][1],p[1][0],p[1][1]);
				d= (float)(Math.sqrt(Math.pow((p[1][0]-p[0][0]), 2)+Math.pow((p[1][1]-p[0][1]), 2)));
				t=(float)Math.atan2(dy,Math.abs(dx));
				t=(float)(t*180/Math.PI);
				if(d>=100)
				{
					d=100;
				}
				g.drawString(d+"  "+s+t,p[1][0],p[1][1]-20);
			}
			if(p1)
			{	
				int k[]=eq(t,d,w,player);
				for(int j=15;j<k.length-1&&(!player);j++)
				{ 
					busy=true;
					if(dir)
					{
						g.setColor(Color.RED);
						g.drawImage(ball,pa+j+45,510-k[j-15],this);
						try
						{
							Thread.sleep(3);
						}
						catch(Exception e)
						{
						}
						g.setColor(new Color(145,145,145));
						g.drawImage(ball1,pa+j+45,510-k[j-15],this);
					}	
					else
					{
						g.setColor(Color.RED);
						g.drawImage(ball,pa-j+71,510-k[j-15],this);
						try
						{
							Thread.sleep(3);
						}
						catch(Exception e)
						{
						}
						g.setColor(new Color(145,145,145));
						g.drawImage(ball1,pa-j+71,510-k[j-15],this);
					}
					la=true;
					busy=false;
				}
				if(!player&&la&&dir)
				{
					if(r>=(dist+25)&&(r<=(dist+35)))
						{
							b1-=3;
						}
					else if(r>=(dist+10)&&(r<=(dist+50)))
							{
								b1-=2;
							}
							else if(r>=dist&&(r<=(dist+60)))
								   {
										b1-=1;
								   }
					la=false;
				}
				for(int j=15;j<k.length-1&&player;j++)
				{ 
					busy=true;
					if(dir)
					{
						g.setColor(Color.YELLOW);
						g.drawImage(ball,pb-j+15,510-k[j-15],this);
						try
						{
							Thread.sleep(3);
						}
						catch(Exception e)
						{
						}
						g.setColor(new Color(145,145,145));
						g.drawImage(ball1,pb-j+15,510-k[j-15],this);
					}
					else
					{
						g.setColor(Color.YELLOW);
						g.drawImage(ball,pb+j-15,510-k[j-15],this);
						try
						{
							Thread.sleep(3);
						}
						catch(Exception e)
						{
						}
						g.setColor(new Color(145,145,145));
						g.drawImage(ball1,pb+j-15,510-k[j-15],this);
					}
					lb=true;
					busy=false;
				}
				if(player&&lb&&dir)
				{
					if(r>=(dist+25)&&(r<=(dist+35)))
						{	
							a1-=3;
						}
						else if(r>=(dist+10)&&(r<=(dist+50)))
							{
								a1-=2;
							}
							else if(r>=dist&&(r<=(dist+60)))
								{
									a1-=1;
								}
						lb=false;
				}
				p1=false;
			}
		}
	}
	public int[] eq(double a,double p,int w,boolean player)
	{ 
	    int x=0,i=0;
	    double vx;
	    if(!player)
	    {
			vx=(p*Math.cos(a*3.14/180)+w);
	    }
	    else
	    {
		 	vx=(p*Math.cos(a*3.14/180)-w);
	    }
	    double vy=p*Math.sin(a*3.14/180);
	    double t=(vy/9.81+Math.sqrt((vy*vy/96.2361)+9.174));
	    r=(int)(vx*t);
	    int c[]=new int[r+1];
		while(x<=r)
			{ 
				c[x]=(int)(vy*(x/vx)-4.905*(x/vx)*(x/vx));
				x++;
			}
	return c;
	}
}
