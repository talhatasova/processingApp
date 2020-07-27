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

  void anaMenu()
  {
    image(logo, 250, 100, 700, 300);
    image(start, 450, 465, 300, 70);
    image(high_scores, 450, 565, 300, 70);
    image(how_to_play, 450, 665, 300, 70); 
    image(quit, 450, 765, 300, 70);
  }
  void highScore()
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
        if(i==0 || j==0) fill(#CF9CF5);
        else fill(#3FE8DE);
        text(scoreTable[i][j], 200*j+50, 100*(i+1));
      }
    }
    
    image(reset, 500,750, 200, 50);
    image(back, 100, 750, 200, 50);
    image(quit, 900, 750, 200, 50);
  }
  void howToPlay()
  {
    image(how_to_play_text, 50, 50, 1100, 700);
    image(back, 50, 800, 200, 50);
    image(quit, 950, 800, 200, 50);
  }

  int getScore()
  {
    return sifirSayisi*10 + (120-second())*2;
  }

  void highScoreKeeper(int currentScore)
  {
    highScores = loadStrings("highScores.txt");
    String allData = join(highScores, "   ");
    scoreData = split(allData, "   ");
    scores.append(int(scoreData[4]));
    scores.append(int(scoreData[9]));
    scores.append(int(scoreData[14]));
    scores.append(int(scoreData[19]));
    scores.append(int(scoreData[24]));

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
  void gameOver(int score)
  { 
    image(logo, 250, 100, 700, 200);
    image(game_over, 250, 100, 700, 500);
    image(play_again, 450, 565, 300, 70);
    image(high_scores, 450, 665, 300, 70);
    image(quit, 450, 765, 300, 70);
    textFont(font);
    text(("Score: " + score), 530, 475);
  }

  void playing()
  {
    textFont(font);
    text(("Elapsed Time: " + second()), 900, 200);
    text(("Number of Moves: " + hamleSayisi), 900, 300);
    text(("Number of Zeros: " + sifirSayisi), 900, 400);
    image(restart, 875, 575, 300, 70);
    image(back, 875, 675, 300, 70);
    image(quit, 875, 775, 300, 70);
  }
  
  void sifirla()
  {
    String[] resetScores = new String[5];
    for (int i=0; i<5; i++)
    {
      resetScores[i] = "1.1.2020   00:00   0   0   0";
      saveStrings("highScores.txt", resetScores);
    }
  }
}
