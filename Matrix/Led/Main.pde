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

void setup()
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

void draw()
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

int sifirSayac(ArrayList<ArrayList<Leds>> leds)
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
int[][] uc_carpi_uc(Leds led)
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

void mouseClicked()
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
