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

  void Ciz()
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
  
  int DetHesapla(int[][] det_matrix)
  {
    return det_matrix[0][0]*(det_matrix[1][1]*det_matrix[2][2]-det_matrix[1][2]*det_matrix[2][1])+
           det_matrix[1][0]*(det_matrix[0][1]*det_matrix[2][2]-det_matrix[0][2]*det_matrix[2][1])+
           det_matrix[2][0]*(det_matrix[0][1]*det_matrix[1][2]-det_matrix[0][2]*det_matrix[1][1]);
  }
}
