/**
 * Mengatur elemen snake / ular di dalam game
 *
 * @author (Arvel Gavrilla R. , Raihan Alifianto)
 * @version (10 - 1 - 2021)
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Snake {
    public static final int SNAKEDIMEN = 25;
    private static final int MINWIDTH = 25;
    private static final int MINHEIGHT = 25;
    private static final int MAXWIDTH = 450;
    private static final int MAXHEIGHT = 450;
    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
    private ArrayList<Location> snakeloc = new ArrayList();
    private ImageIcon headimage;
    private ImageIcon bodyimage;
    private int lengthOfSnake;
    private Location loc;

    public Snake() {
        for(int i = 0; i < 3; ++i) {
            this.snakeloc.add(new Location(100 - 25 * i, 50));
        }
        //mendapatkan tampilan dari ular mulai dari kepala hingga badannya
        java.net.URL headresource = getClass().getResource("snakehead.png");
        this.headimage = new ImageIcon(headresource);
        java.net.URL bodyresource = getClass().getResource("snakebody.png");
        this.bodyimage = new ImageIcon(bodyresource);
    }
    //mendapatkan lokasi dari ularnya
    public ArrayList<Location> getSnakeloc() {
        return this.snakeloc;
    }
    public void setLoc(int i, Location loc) {
        this.snakeloc.set(i, loc);
    }
    //mengatur pertambahan panjang dari ularnya
    public void grow() {
        int x = ((Location)this.snakeloc.get(this.snakeloc.size() - 1)).getX();
        int y = ((Location)this.snakeloc.get(this.snakeloc.size() - 1)).getY();
        this.snakeloc.add(new Location(x, y));
    }

    public ImageIcon getHeadimage() {
        return this.headimage;
    }
    public ImageIcon getBodyimage() {
        return this.bodyimage;
    }

    public void render(Graphics g, Renderer renderer) {
        for(int i = 0; i < this.snakeloc.size(); ++i) {
            int x = ((Location)this.snakeloc.get(i)).getX();
            int y = ((Location)this.snakeloc.get(i)).getY();
            if (i == 0) {
                this.headimage.paintIcon(renderer, g, x, y);
            } else {
                this.bodyimage.paintIcon(renderer, g, x, y);
            }
        }

    }
    //mengatur bila koordinat makanan dan ular sama maka didefinisikan ular sedang memakan makanannya
    public boolean eat(Food food) {

        return ((Location)this.snakeloc.get(0)).getX() == food.getLocation().getX() &&
                ((Location)this.snakeloc.get(0)).getY() == food.getLocation().getY();

    }
    //mendefinisikan pergerakan ular
    public boolean move(int move) {
        int i;
        this.lengthOfSnake = this.snakeloc.size();
        label79:
        switch(move) {
            case 0:
                i = this.lengthOfSnake - 2;

                while(true) {
                    if (i < 0) {
                        break label79;
                    }

                    this.loc = (Location)this.snakeloc.get(i);
                    this.setLoc(i + 1, this.loc);
                    if (i == 0) {
                        this.setLoc(i, new Location(this.loc.getX(), this.loc.getY() - 25));
                        if (this.loc.getY() <= 25) {
                            this.setLoc(0, new Location(this.loc.getX(), 425));
                        }
                    }

                    --i;
                }
            case 1:
                i = this.lengthOfSnake - 2;

                while(true) {
                    if (i < 0) {
                        break label79;
                    }

                    this.loc = (Location)this.snakeloc.get(i);
                    this.setLoc(i + 1, this.loc);
                    if (i == 0) {
                        this.setLoc(i, new Location(this.loc.getX() + 25, this.loc.getY()));
                        if (this.loc.getX() >= 425) {
                            this.setLoc(0, new Location(25, this.loc.getY()));
                        }
                    }

                    --i;
                }
            case 2:
                i = this.lengthOfSnake - 2;

                while(true) {
                    if (i < 0) {
                        break label79;
                    }

                    this.loc = (Location)this.snakeloc.get(i);
                    this.setLoc(i + 1, this.loc);
                    if (i == 0) {
                        this.setLoc(i, new Location(this.loc.getX(), this.loc.getY() + 25));
                        if (this.loc.getY() >= 425) {
                            this.setLoc(0, new Location(this.loc.getX(), 25));
                        }
                    }

                    --i;
                }
            case 3:
                for(i = this.lengthOfSnake - 2; i >= 0; --i) {
                    this.loc = (Location)this.snakeloc.get(i);
                    this.setLoc(i + 1, this.loc);
                    if (i == 0) {
                        this.setLoc(i, new Location(this.loc.getX() - 25, this.loc.getY()));
                        if (this.loc.getX() <= 25) {
                            this.setLoc(0, new Location(425, this.loc.getY()));
                        }
                    }
                }
        }
        
        for(i = 1; i < this.lengthOfSnake; ++i) {
            if (((Location)this.snakeloc.get(0)).getX() == ((Location)this.snakeloc.get(i)).getX() &&
                    ((Location)this.snakeloc.get(0)).getY() == ((Location)this.snakeloc.get(i)).getY()) {
                return false;
            }
            //bila panjangnya sudah melebihi 20 maka ular akan berganti warna
            if(this.lengthOfSnake >= 20){
                java.net.URL bodyresource = getClass().getResource("snakebodyGrow.png");
                this.bodyimage = new ImageIcon(bodyresource);
            }
        }

        return true;
    }
}
