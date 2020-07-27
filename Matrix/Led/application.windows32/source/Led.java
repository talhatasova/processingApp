import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Led extends PApplet {

class Leds
{
  public int x;
  public int y;
  public int isim;

  PImage eksi_iki = loadImage("eksi_iki.png");
  PImage eksi_bir = loadImage("eksi_bir.png");
  PImage sifir = loadImage("sifir.png");
  PImage arti_bir = loadImage("arti_bir.png");
  PImage arti_iki = loadImage("arti_iki.png");
  PImage cerceve_eksi_iki = loadImage("cerceve_eksi_iki.png");
  PImage cerceve_eksi_bir = loadImage("cerceve_eksi_bir.png");
  PImage cerceve_sifir = loadImage("cerceve_sifir.png");
  PImage cerceve_arti_bir = loadImage("cerceve_arti_bir.png");
  PImage cerceve_arti_iki = loadImage("cerceve_arti_iki.png");
  

  Leds(int x, int y, int isim)
  {
    this.x = x;
    this.y = y;
    this.isim = isim;
  }

  public void Ciz()
  {
    image(cerceve_eksi_iki, 175, 32, 30, 30);
    image(cerceve_eksi_bir, 305, 32, 30, 30);
    image(cerceve_arti_bir, 565, 32, 30, 30);
    image(cerceve_arti_iki, 695, 32, 30, 30);
    
    if (isim == -2) image(eksi_iki, x*75+112, y*75+112, 50, 50);
    else if (isim == -1) image(eksi_bir, x*75+112, y*75+112, 50, 50);
    else if (isim == 0) image(sifir, x*75+112, y*75+112, 50, 50);
    else if (isim == 1) image(arti_bir, x*75+112, y*75+112, 50, 50);
    else if (isim == 2) image(arti_iki, x*75+112, y*75+112, 50, 50);
  }
  
  public int DetHesapla(int[][] det_matrix)
  {
    return det_matrix[0][0]*(det_matrix[1][1]*det_matrix[2][2]-det_matrix[1][2]*det_matrix[2][1])+
           det_matrix[1][0]*(det_matrix[0][1]*det_matrix[2][2]-det_matrix[0][2]*det_matrix[2][1])+
           det_matrix[2][0]*(det_matrix[0][1]*det_matrix[1][2]-det_matrix[0][2]*det_matrix[1][1]);
  }
}
class Frame extends Timer
{
  IntList scores = new IntList();
  PImage quit = loadImage("quit.png");
  PImage start = loadImage("start.png");
  PImage how_to_play = loadImage("how_to_play.png");
  PImage back = loadImage("back.png");
  PImage play_again = loadImage("play_again.png");
  PImage logo = loadImage("logo.png");
  PImage high_scores = loadImage("high_scores.png");
  PImage game_over = loadImage("game_over.png");
  PImage restart = loadImage("restart.png");
  PImage reset = loadImage("reset.png");
  PImage how_to_play_text = loadImage("how_to_play_text.PNG");
  
  PFont  font = createFont("Times New Roman", 30);

  String[][] scoreTable = new String[6][6];
  String[] highScores;
  String[] scoreData;
  String[] newData = new String[25];
  String[] uploadedScores = new String[5];

  public void anaMenu()
  {
    image(logo, 250, 100, 700, 300);
    image(start, 450, 465, 300, 70);
    image(high_scores, 450, 565, 300, 70);
    image(how_to_play, 450, 665, 300, 70); 
    image(quit, 450, 765, 300, 70);
  }
  public void highScore()
  {
    highScores = loadStrings("highScores.txt");
    String allData = join(highScores, "   ");
    scoreData = split(allData, "   ");
    int index = 0;
    
    scoreTable[0][0] = "";
    scoreTable[0][1] = "Date";
    scoreTable[0][2] = "Time";
    scoreTable[0][3] = "Zeros";
    scoreTable[0][4] = "Clock";
    scoreTable[0][5] = "SCORE";
    for(int i=1; i<6; i++) scoreTable[i][0] = str(i)+")";
    for(int j=1; j<6; j++)
    {
      for(int k=1; k<6; k++)
      {
        scoreTable[j][k] = scoreData[index];
        index++;
      }
    }
    
    textSize(32);
    for(int i=0; i<6; i++)
    {
      for(int j=0; j<6; j++)
      {
        if(i==0 || j==0) fill(0xffCF9CF5);
        else fill(0xff3FE8DE);
        text(scoreTable[i][j], 200*j+50, 100*(i+1));
      }
    }
    
    image(reset, 500,750, 200, 50);
    image(back, 100, 750, 200, 50);
    image(quit, 900, 750, 200, 50);
  }
  public void howToPlay()
  {
    image(how_to_play_text, 50, 50, 1100, 700);
    image(back, 50, 800, 200, 50);
    image(quit, 950, 800, 200, 50);
  }

  public int getScore()
  {
    return sifirSayisi*10 + (120-second())*2;
  }

  public void highScoreKeeper(int currentScore)
  {
    highScores = loadStrings("highScores.txt");
    String allData = join(highScores, "   ");
    scoreData = split(allData, "   ");
    scores.append(PApplet.parseInt(scoreData[4]));
    scores.append(PApplet.parseInt(scoreData[9]));
    scores.append(PApplet.parseInt(scoreData[14]));
    scores.append(PApplet.parseInt(scoreData[19]));
    scores.append(PApplet.parseInt(scoreData[24]));

    for (int i=0; i<5; i++)
    {
      if (currentScore > scores.get(i))
      {
        for (int j=0; j<=5*i-1; j += 5)
        {
          newData[j] = scoreData[j];
          newData[j+1] = scoreData[j+1];
          newData[j+2] = scoreData[j+2];
          newData[j+3] = scoreData[j+3];
          newData[j+4] = scoreData[j+4];
        }

        newData[5*i] = str(day())+"."+str(month())+"."+str(year());
        newData[5*i+1] = str(hour())+":"+str(minute());
        newData[5*i+2] = str(sifirSayisi);
        newData[5*i+3] = str(120-second());
        newData[5*i+4] = str(currentScore);

        for (int k=5*i+5; k<24; k += 5)
        {
          newData[k] = scoreData[k-5];
          newData[k+1] = scoreData[k-4];
          newData[k+2] = scoreData[k-3];
          newData[k+3] = scoreData[k-2];
          newData[k+4] = scoreData[k-1];
        }
        break;
      }
    }

    for (int b=0; b<5; b++)
    {
      uploadedScores[b] = newData[b*5] + "   " + newData[5*b+1] + "   " + newData[5*b+2] + "   " + newData[5*b+3] + "   " + newData[5*b+4]; 
    }
    saveStrings("highScores.txt",uploadedScores);
    String a[] = loadStrings("highScores.txt");
    printArray(a);
  }
  public void gameOver(int score)
  { 
    image(logo, 250, 100, 700, 200);
    image(game_over, 250, 100, 700, 500);
    image(play_again, 450, 565, 300, 70);
    image(high_scores, 450, 665, 300, 70);
    image(quit, 450, 765, 300, 70);
    textFont(font);
    text(("Score: " + score), 530, 475);
  }

  public void playing()
  {
    textFont(font);
    text(("Elapsed Time: " + second()), 900, 200);
    text(("Number of Moves: " + hamleSayisi), 900, 300);
    text(("Number of Zeros: " + sifirSayisi), 900, 400);
    image(restart, 875, 575, 300, 70);
    image(back, 875, 675, 300, 70);
    image(quit, 875, 775, 300, 70);
  }
  
  public void sifirla()
  {
    String[] resetScores = new String[5];
    for (int i=0; i<5; i++)
    {
      resetScores[i] = "1.1.2020   00:00   0   0   0";
      saveStrings("highScores.txt", resetScores);
    }
  }
}
ArrayList<ArrayList<Leds>> leds = new ArrayList<ArrayList<Leds>>();
IntList isimler = new IntList();

Leds[][] kontrolMatrisi = new Leds[10][10];
int[][] det_bulma_matrisi = new int[3][3];
Frame frame;

int isim;

public int hamleSayisi = 0;
public int sifirSayisi = 0;

int detToplam = 0;
int oyunState = 0;

public void setup()
{
  frame = new Frame();
  leds.clear();
  isimler.append(-2);
  isimler.append(-1);
  isimler.append(1);
  isimler.append(2);
  
  hamleSayisi = 0;
  sifirSayisi = 0;
  
  for (int i=0; i<10; i++)
  {
    leds.add(new ArrayList());
    for (int j=0; j<10; j++)
    { 
      leds.get(i).add(new Leds(i, j, isimler.get(floor(random(4))))); 
    }
  }
}

public void settings() {
  size(1200, 900);
}

public void draw()
{
  background(0);
  if(oyunState == 0) //ana menu
  {
    frame.anaMenu();
  }
  else if (oyunState == 1) //start
  {
    frame.playing();
    sifirSayac(leds);
    for (int i=0; i<leds.size(); i++) for (int j=0; j<leds.get(i).size(); j++) leds.get(i).get(j).Ciz();
  }
  else if (oyunState == 2) //how_to_play
  {
    frame.howToPlay();
  }
  else if (oyunState == 3) //quit
  {
    exit();
  }
  else if (oyunState == 4) //game_over
  {
    frame.gameOver(frame.getScore());
  }
  else if (oyunState == 5) //high scores
  {
    frame.highScore();
  }
}

public int sifirSayac(ArrayList<ArrayList<Leds>> leds)
{
  sifirSayisi = 0;
  
  for (int i=0; i<leds.size(); i++)
  {
    for (int j=0; j<leds.get(i).size(); j++)
    {
      if(leds.get(i).get(j).isim == 0)
      {
        sifirSayisi++;
      }
    }
  }
  return sifirSayisi;
}
public int[][] uc_carpi_uc(Leds led)
{
  for (int i=0; i<3; i++)
  {
    for (int j=0; j<3; j++)
    {
      if (led.x == 0 && led.y != 0 && led.y != 9)
      {
        det_bulma_matrisi[0][0] = leds.get(9).get(led.y-1).isim;
        det_bulma_matrisi[0][1] = leds.get(9).get(led.y).isim;
        det_bulma_matrisi[0][2] = leds.get(9).get(led.y+1).isim;
        det_bulma_matrisi[1][0] = leds.get(0).get(led.y-1).isim;
        det_bulma_matrisi[1][1] = leds.get(0).get(led.y).isim;
        det_bulma_matrisi[1][2] = leds.get(0).get(led.y+1).isim;
        det_bulma_matrisi[2][0] = leds.get(1).get(led.y-1).isim;
        det_bulma_matrisi[2][1] = leds.get(1).get(led.y).isim;
        det_bulma_matrisi[2][2] = leds.get(1).get(led.y+1).isim;
      }
      else if (led.y == 0 && led.x != 0 && led.x != 9)
      {
        det_bulma_matrisi[0][0] = leds.get(led.x-1).get(9).isim;
        det_bulma_matrisi[0][1] = leds.get(led.x-1).get(0).isim;
        det_bulma_matrisi[0][2] = leds.get(led.x-1).get(1).isim;
        det_bulma_matrisi[1][0] = leds.get(led.x).get(9).isim;
        det_bulma_matrisi[1][1] = leds.get(led.x).get(0).isim;
        det_bulma_matrisi[1][2] = leds.get(led.x).get(1).isim;
        det_bulma_matrisi[2][0] = leds.get(led.x+1).get(9).isim;
        det_bulma_matrisi[2][1] = leds.get(led.x+1).get(0).isim;
        det_bulma_matrisi[2][2] = leds.get(led.x+1).get(1).isim;
      }
      else if (led.x == 9 && led.y != 0 && led.y != 9)
      {
        det_bulma_matrisi[0][0] = leds.get(8).get(led.y-1).isim;
        det_bulma_matrisi[0][1] = leds.get(8).get(led.y).isim;
        det_bulma_matrisi[0][2] = leds.get(8).get(led.y+1).isim;
        det_bulma_matrisi[1][0] = leds.get(9).get(led.y-1).isim;
        det_bulma_matrisi[1][1] = leds.get(9).get(led.y).isim;
        det_bulma_matrisi[1][2] = leds.get(9).get(led.y+1).isim;
        det_bulma_matrisi[2][0] = leds.get(0).get(led.y-1).isim;
        det_bulma_matrisi[2][1] = leds.get(0).get(led.y).isim;
        det_bulma_matrisi[2][2] = leds.get(0).get(led.y+1).isim;
      }
      else if (led.y == 9 && led.x != 0 && led.x != 9)
      {
        det_bulma_matrisi[0][0] = leds.get(led.x-1).get(8).isim;
        det_bulma_matrisi[0][1] = leds.get(led.x-1).get(9).isim;
        det_bulma_matrisi[0][2] = leds.get(led.x-1).get(0).isim;
        det_bulma_matrisi[1][0] = leds.get(led.x).get(8).isim;
        det_bulma_matrisi[1][1] = leds.get(led.x).get(9).isim;
        det_bulma_matrisi[1][2] = leds.get(led.x).get(0).isim;
        det_bulma_matrisi[2][0] = leds.get(led.x+1).get(8).isim;
        det_bulma_matrisi[2][1] = leds.get(led.x+1).get(9).isim;
        det_bulma_matrisi[2][2] = leds.get(led.x+1).get(0).isim;
      }
      else if (led.y == 0 && led.x == 0)
      {
        det_bulma_matrisi[0][0] = leds.get(9).get(9).isim;
        det_bulma_matrisi[0][1] = leds.get(9).get(0).isim;
        det_bulma_matrisi[0][2] = leds.get(9).get(1).isim;
        det_bulma_matrisi[1][0] = leds.get(0).get(9).isim;
        det_bulma_matrisi[1][1] = leds.get(0).get(0).isim;
        det_bulma_matrisi[1][2] = leds.get(0).get(1).isim;
        det_bulma_matrisi[2][0] = leds.get(1).get(9).isim;
        det_bulma_matrisi[2][1] = leds.get(1).get(0).isim;
        det_bulma_matrisi[2][2] = leds.get(1).get(1).isim;
      }
      else if (led.y == 0 && led.x == 9)
      {
        det_bulma_matrisi[0][0] = leds.get(8).get(9).isim;
        det_bulma_matrisi[0][1] = leds.get(8).get(0).isim;
        det_bulma_matrisi[0][2] = leds.get(8).get(1).isim;
        det_bulma_matrisi[1][0] = leds.get(9).get(9).isim;
        det_bulma_matrisi[1][1] = leds.get(9).get(0).isim;
        det_bulma_matrisi[1][2] = leds.get(9).get(1).isim;
        det_bulma_matrisi[2][0] = leds.get(0).get(9).isim;
        det_bulma_matrisi[2][1] = leds.get(0).get(0).isim;
        det_bulma_matrisi[2][2] = leds.get(0).get(1).isim;
      }
      else if (led.y == 9 && led.x == 9)
      {
        det_bulma_matrisi[0][0] = leds.get(8).get(8).isim;
        det_bulma_matrisi[0][1] = leds.get(8).get(9).isim;
        det_bulma_matrisi[0][2] = leds.get(8).get(0).isim;
        det_bulma_matrisi[1][0] = leds.get(9).get(8).isim;
        det_bulma_matrisi[1][1] = leds.get(9).get(9).isim;
        det_bulma_matrisi[1][2] = leds.get(9).get(0).isim;
        det_bulma_matrisi[2][0] = leds.get(0).get(8).isim;
        det_bulma_matrisi[2][1] = leds.get(0).get(9).isim;
        det_bulma_matrisi[2][2] = leds.get(0).get(0).isim;
      }
      else if (led.y == 9 && led.x == 0)
      {
        det_bulma_matrisi[0][0] = leds.get(9).get(8).isim;
        det_bulma_matrisi[0][1] = leds.get(9).get(9).isim;
        det_bulma_matrisi[0][2] = leds.get(9).get(0).isim;
        det_bulma_matrisi[1][0] = leds.get(0).get(8).isim;
        det_bulma_matrisi[1][1] = leds.get(0).get(9).isim;
        det_bulma_matrisi[1][2] = leds.get(0).get(0).isim;
        det_bulma_matrisi[2][0] = leds.get(1).get(8).isim;
        det_bulma_matrisi[2][1] = leds.get(1).get(9).isim;
        det_bulma_matrisi[2][2] = leds.get(1).get(0).isim;
      }
      else
      {
        det_bulma_matrisi[0][0] = leds.get(led.x-1).get(led.y-1).isim;
        det_bulma_matrisi[0][1] = leds.get(led.x-1).get(led.y).isim;
        det_bulma_matrisi[0][2] = leds.get(led.x-1).get(led.y+1).isim;
        det_bulma_matrisi[1][0] = leds.get(led.x).get(led.y-1).isim;
        det_bulma_matrisi[1][1] = leds.get(led.x).get(led.y).isim;
        det_bulma_matrisi[1][2] = leds.get(led.x).get(led.y+1).isim;
        det_bulma_matrisi[2][0] = leds.get(led.x+1).get(led.y-1).isim;
        det_bulma_matrisi[2][1] = leds.get(led.x+1).get(led.y).isim;
        det_bulma_matrisi[2][2] = leds.get(led.x+1).get(led.y+1).isim;
      }
    }
  }
  return det_bulma_matrisi;
}

public void mouseClicked()
{ 
  if(oyunState == 0) //ana menüdeyken
  {
    if (mouseX > 450 && mouseX < 750 && mouseY > 665 && mouseY < 735) oyunState = 2; // how to play
    else if (mouseX > 450 && mouseX < 750 && mouseY > 565 && mouseY < 635) oyunState = 5; // high score
    else if (mouseX > 450 && mouseX < 750 && mouseY > 765 && mouseY < 835) oyunState = 3; //quit
    else if (mouseX > 450 && mouseX < 750 && mouseY > 465 && mouseY < 535) // start
    {
      setup();
      oyunState = 1;
      frame.start(); // time starts
    }
  }
  
  else if(oyunState == 4) //gameOver
  {
    if (mouseX > 450 && mouseX < 750 && mouseY < 835 && mouseY > 765) oyunState = 3; //quit
    else if (mouseX > 100 && mouseX < 300 && mouseY < 850 && mouseY > 750) oyunState = 0; // back to main menu
    else if (mouseX > 450 && mouseX < 750 && mouseY < 735 && mouseY > 665) oyunState = 5; // high score screen
    else if (mouseX > 450 && mouseX < 750 && mouseY < 635 && mouseY > 565) // play again
    {
      setup();
      oyunState = 1; // play again
      frame.start();
    }
     
  }
  else if(oyunState == 2) //how to play'deyken
  {
    if (mouseX > 900 && mouseX < 1100 && mouseY < 850 && mouseY > 750) oyunState = 3; // quit
    else if (mouseX > 100 && mouseX < 300 && mouseY < 850 && mouseY > 750) oyunState = 0; // back to main menu
  }
  
  else if(oyunState == 5) // high score'dayken
  {
    if (mouseX > 900 && mouseX < 1100 && mouseY < 850 && mouseY > 750) oyunState = 3; // quit
    else if (mouseX > 100 && mouseX < 300 && mouseY < 850 && mouseY > 750) oyunState = 0; // back to main menu
    else if  (mouseX > 500 && mouseX < 700 && mouseY < 850 && mouseY > 750) frame.sifirla(); // reset the high scores 
  }
  
  
  else if(oyunState == 1) //oyundayken
  {
  if (mouseX > 875 && mouseX < 1175 && mouseY < 845 && mouseY > 775) oyunState = 3; // quit
  else if (mouseX > 875 && mouseX < 1175 && mouseY < 745 && mouseY > 675) oyunState = 0; // back to main menu
  else if (mouseX > 875 && mouseX < 1175 && mouseY < 645 && mouseY > 575) 
  {
    frame.stop();
    setup(); // restart
    frame.start();
  }
  else if (mouseX > 175 && mouseX < 205 && mouseY < 62 && mouseY > 32) // -2'ye basılırsa
    {
      for (int i=0; i<10; i++)
      {
        for (int j=0; j<10; j++)
        {
          if(leds.get(i).get(j).isim == -2)
          {
                 if(leds.get(i).get(j).DetHesapla(uc_carpi_uc(leds.get(i).get(j))) > 0)      leds.get(i).set(j, new Leds(i, j, -1)); 
            else if(leds.get(i).get(j).DetHesapla(uc_carpi_uc(leds.get(i).get(j))) < 0)      leds.get(i).set(j, new Leds(i, j, 2));
          }
        }
      }
      hamleSayisi ++;
    }
  
  else if (mouseX > 305 && mouseX < 335 && mouseY < 62 && mouseY > 32) // -1'e basılırsa
  {
    for (int i=0; i<10; i++)
    {
      for (int j=0; j<10; j++)
      {
        if(leds.get(i).get(j).isim == -1)
        {
               if(leds.get(i).get(j).DetHesapla(uc_carpi_uc(leds.get(i).get(j))) > 0)      leds.get(i).set(j, new Leds(i, j, 0)); 
          else if(leds.get(i).get(j).DetHesapla(uc_carpi_uc(leds.get(i).get(j))) < 0)      leds.get(i).set(j, new Leds(i, j, -2));
        }
      }
    }
    hamleSayisi ++;
  }
  
  else if (mouseX > 565 && mouseX < 595 && mouseY < 62 && mouseY > 32) // +1'e basılırsa
  {
    for (int i=0; i<10; i++)
    {
      for (int j=0; j<10; j++)
      {
        if(leds.get(i).get(j).isim == 1)
        {
               if(leds.get(i).get(j).DetHesapla(uc_carpi_uc(leds.get(i).get(j))) > 0)      leds.get(i).set(j, new Leds(i, j, 2)); 
          else if(leds.get(i).get(j).DetHesapla(uc_carpi_uc(leds.get(i).get(j))) < 0)      leds.get(i).set(j, new Leds(i, j, 0));
        }
      }
    }
    hamleSayisi ++;
  }
  
  else if (mouseX > 695 && mouseX < 725 && mouseY < 62 && mouseY > 32) // +2'ye basılırsa
  {
    for (int i=0; i<10; i++)
    {
      for (int j=0; j<10; j++)
      {
        if(leds.get(i).get(j).isim == 2)
        {
               if(leds.get(i).get(j).DetHesapla(uc_carpi_uc(leds.get(i).get(j))) > 0)      leds.get(i).set(j, new Leds(i, j, -2)); 
          else if(leds.get(i).get(j).DetHesapla(uc_carpi_uc(leds.get(i).get(j))) < 0)      leds.get(i).set(j, new Leds(i, j, 1));
        }
      }
    }
    hamleSayisi ++;
  }
  
  if(hamleSayisi == 10 || frame.second() == 120) 
    { 
      oyunState = 4; //game over
      frame.stop();
      frame.highScoreKeeper(frame.getScore());
      hamleSayisi = 0;
    }
  }
}
class Timer {
  int startTime = 0, stopTime = 0;
  boolean running = false;  
  
    
    public void start() {
        startTime = millis();
        running = true;
    }
    public void stop() {
        stopTime = millis();
        running = false;
    }
    public int getElapsedTime() {
        int elapsed;
        if (running) {
             elapsed = (millis() - startTime);
        }
        else {
            elapsed = (stopTime - startTime);
        }
        return elapsed;
    }
    public int second() {
      return (getElapsedTime() / 1000) % 60;
    }
    
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Led" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
