package com.kilavuz.kelimeoyunu3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    TextView yanit, tanimtv, puan, zaman, tahminSuresi, yanlisCevap, seviye;
    Button tahminEt, harfAl, tahminOnay, sonraki;
    MediaPlayer tiktak, fail, music, basari;
    String asteriks, newasteriks;
    String tanim = null;
    EditText tahmin;
    public Boolean cancelTimer = false;
    public Boolean cancelTimer2 = false;
    String word;
    public Boolean win = true;
    ImageButton volumeOn, volumeOff;
    public int sayac = 70;
    public int skor;
    public int harfSayisi = 6;
    public int soru = 0;
    private CountDownTimer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        yanit = findViewById(R.id.yanit_tv);
        tanimtv = findViewById(R.id.tanim_tv);
        tahminEt = findViewById(R.id.tahminEt_btn);
        harfAl = findViewById(R.id.harfAl_btn);
        puan = findViewById(R.id.puan_tv);
        zaman = findViewById(R.id.timer_tv);
        tahmin = findViewById(R.id.tahmin_et);
        tahminOnay = findViewById(R.id.tahminOnay_btn);
        tahminSuresi = findViewById(R.id.tahminSuresi_tv);
        sonraki = findViewById(R.id.sonraki_btn);
        yanlisCevap = findViewById(R.id.yanlisCevap_tv);
        seviye = findViewById(R.id.seviye_tv);
        volumeOff = findViewById(R.id.volumeOff_btn);
        volumeOn = findViewById(R.id.volumeOn_btn);
        tiktak = MediaPlayer.create(getApplicationContext(), R.raw.countdown);
        fail = MediaPlayer.create(getApplicationContext(), R.raw.fail);
        basari = MediaPlayer.create(getApplicationContext(), R.raw.basari);
        music = MediaPlayer.create(getApplicationContext(), R.raw.music);
        skor = 4000;

        music.start();
        volumeOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                music.pause();
                volumeOn.setVisibility(View.INVISIBLE);
                volumeOff.setVisibility(View.VISIBLE);
            }
        });
        volumeOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                music.start();
                volumeOff.setVisibility(View.INVISIBLE);
                volumeOn.setVisibility(View.VISIBLE);
            }
        });

        timer = new CountDownTimer(71100, 1000) {

            @Override
            public void onTick(long l) {


                zaman.setText("Kalan S??re: " + sayac--);
                if (cancelTimer.equals(true)) {
                    cancel();
                }
                if (sayac < 10) {
                    tiktak.start();
                }
            }

            @Override
            public void onFinish() {
                music.pause();
                win = false;
                cancelTimer2 = true;
                Intent i2 = new Intent(MainActivity2.this, gameOverActivity.class);
                i2.putExtra("win1", win);
                i2.putExtra("skor1", String.valueOf(skor));
                i2.putExtra("sayac1", String.valueOf(sayac));
                i2.putExtra("soru1", String.valueOf(soru));
                startActivity(i2);
                finish();
            }
        };
        timer.start();

        String[] words = {"hademe", "adalet", "icraat", "kadraj", "patika"};

        word = words[(int) (Math.random() * words.length)];
        if (word.equals("hademe")) {
            tanim = "Bir i??yerinde temizlik ve ayak i??lerine bakan kimse, odac??.\n";
        } else if (word.equals("adalet")) {
            tanim = "Hak ve hukuka uygunluk; hak ve hukuku g??zetme ve yerine getirme; do??ruluk.";
        } else if (word.equals("icraat")) {
            tanim = "Yap??lan i??ler, ??al????malar, uygulamalar.";
        } else if (word.equals("kadraj")) {
            tanim = "Foto??raf makinas??n??n ve kameran??n viz??r??nde g??r??nen g??r??nt??d??r.";
        } else if (word.equals("patika")) {
            tanim = "Engebeli bir k??rsal arazide iki nokta aras??ndaki en k??sa yolun insan ve" +
                    " hayvan ge??i??leri nedeniyle bitkiden ar??narak olu??mas??.";
        }

        asteriks = new String(new char[word.length()]).replace("\0", "*");
        yanit.setText(asteriks);
        tanimtv.setText("Tan??m: " + tanim);
        tahminEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tahmin.setVisibility(view.VISIBLE);
                tahminOnay.setVisibility(view.VISIBLE);
                tahminSuresi.setVisibility(view.VISIBLE);
                harfAl.setVisibility(view.INVISIBLE);
                tahminEt.setVisibility(view.INVISIBLE);
                zaman.setVisibility(view.INVISIBLE);
                cancelTimer = true;
                cancelTimer2 = false;
                new CountDownTimer(21100, 1000) {
                    int sayac2 = 20;

                    @Override
                    public void onTick(long l) {

                        tahminSuresi.setText("TAHM??N S??RES??: " + sayac2--);
                        if (cancelTimer2.equals(true)) {
                            cancel();
                        }
                        if (sayac2 < 5) {
                            tiktak.start();
                        }
                    }

                    @Override
                    public void onFinish() {
                        fail.start();
                        tahmin.setVisibility(view.INVISIBLE);
                        tahminOnay.setVisibility(view.INVISIBLE);
                        yanlisCevap.setVisibility(View.INVISIBLE);
                        sonraki.setVisibility(View.VISIBLE);
                        if (soru == 4) {
                            sonraki.setText("Bitir");
                        }
                        int sayac3 = 0;
                        for (int i = 0; i < word.length(); i++) {
                            if (asteriks.charAt(i) == '*') {
                                sayac3++;
                            }
                        }
                        skor = skor - (100 * sayac3);

                        puan.setText("Puan: " + skor);
                        yanit.setText(word);
                        yanit.setTextColor(Color.RED);
                        tahminSuresi.setText("S??RE DOLDU!");
                    }
                }.start();
                tahminOnay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String tahminTut = tahmin.getText().toString();
                        if (tahminTut.equals(word)) {
                            basari.start();
                            tahmin.setVisibility(view.INVISIBLE);
                            tahminOnay.setVisibility(view.INVISIBLE);
                            yanlisCevap.setVisibility(View.INVISIBLE);
                            cancelTimer2 = true;
                            yanit.setText(word);
                            yanit.setTextColor(Color.GREEN);
                            sonraki.setVisibility(view.VISIBLE);
                            if (soru == 4) {
                                sonraki.setText("Bitir");
                            }
                        } else {
                            fail.start();
                            yanlisCevap.setVisibility(View.VISIBLE);
                            yanlisCevap.setText("YANLI?? CEVAP!!");
                            tahmin.setText("");
                        }

                    }
                });
            }
        });
        harfAl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                skor -= 100;
                int x = 0;
                Boolean t = false;
                while (t == false) {
                    x = (int) (Math.random() * harfSayisi);
                    if (asteriks.charAt(x) == '*') {
                        t = true;
                    }
                }


                if (x != 0) {
                    newasteriks = asteriks.substring(0, x) + word.charAt(x) + asteriks.substring(x + 1, word.length());
                } else {
                    newasteriks = word.charAt(0) + asteriks.substring(1, word.length());
                }
                yanit.setText(newasteriks);
                puan.setText("Puan: " + skor);
                asteriks = newasteriks;
            }
        });
        sonraki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yanlisCevap.setVisibility(View.INVISIBLE);
                cancelTimer = false;
                new CountDownTimer((sayac * 1000)+1100, 1000) {

                    @Override
                    public void onTick(long l) {


                        zaman.setText("Kalan S??re: " + sayac--);
                        if (cancelTimer.equals(true)) {
                            cancel();
                        }
                        if (sayac < 10) {
                            tiktak.start();
                        }
                    }

                    @Override
                    public void onFinish() {
                        music.pause();
                        win = false;
                        Intent i2 = new Intent(MainActivity2.this, gameOverActivity.class);
                        i2.putExtra("win1", win);
                        i2.putExtra("skor1", String.valueOf(skor));
                        i2.putExtra("sayac1", String.valueOf(sayac));
                        i2.putExtra("soru1", String.valueOf(soru));
                        startActivity(i2);
                        finish();
                    }
                }.start();
                tahmin.setText("");
                switch (soru) {
                    case 0:
                        seviye.setText("2. Soru - 7 Harfli");
                        harfSayisi += 1;
                        tahmin.setVisibility(view.INVISIBLE);
                        tahminOnay.setVisibility(view.INVISIBLE);
                        tahminSuresi.setVisibility(view.INVISIBLE);
                        harfAl.setVisibility(view.VISIBLE);
                        tahminEt.setVisibility(view.VISIBLE);
                        zaman.setVisibility(view.VISIBLE);
                        sonraki.setVisibility(view.INVISIBLE);
                        String[] words = {"jenerik", "taarruz", "serenat", "teselli", "meziyet"};

                        word = words[(int) (Math.random() * words.length)];
                        if (word.equals("jenerik")) {
                            tanim = "Bir filmin ba????ndaki veya sonundaki tan??t??m yaz??lar??.\n";
                        } else if (word.equals("taarruz")) {
                            tanim = "K??t??l??k yapmak, y??pratmak amac??yla, bir kimseye kar???? do??rudan" +
                                    " do??ruya silahl?? veya silahs??z bir eylemde bulunma, h??cum.";
                        } else if (word.equals("serenat")) {
                            tanim = "Geceleyin, a????k havada sevgi duyulan biri i??in bir m??zik arac??yla verilen k??????k konser.";
                        } else if (word.equals("teselli")) {
                            tanim = "Ac?? bir olay?? unutturmaya, ac??s??n?? hafifletmeye ??al????ma, avuntu, avun??.";
                        } else if (word.equals("meziyet")) {
                            tanim = "Bir nesneyi ya da ki??iyi benzerinden ??st??n g??steren nitelik.";
                        }

                        asteriks = new String(new char[word.length()]).replace("\0", "*");
                        yanit.setText(asteriks);
                        yanit.setTextColor(Color.BLACK);
                        tanimtv.setText("Tan??m: " + tanim);
                        soru += 1;
                        break;
                    case 1:
                        seviye.setText("3. Soru - 8 Harfli");
                        harfSayisi += 1;
                        tahmin.setVisibility(view.INVISIBLE);
                        tahminOnay.setVisibility(view.INVISIBLE);
                        tahminSuresi.setVisibility(view.INVISIBLE);
                        harfAl.setVisibility(view.VISIBLE);
                        tahminEt.setVisibility(view.VISIBLE);
                        zaman.setVisibility(view.VISIBLE);
                        sonraki.setVisibility(view.INVISIBLE);
                        String[] words2 = {"biyosfer", "hidrofil", "komplike", "diksiyon", "metropol"};

                        word = words2[(int) (Math.random() * words2.length)];
                        if (word.equals("biyosfer")) {
                            tanim = "D??nya'da canl??lar??n ya??ad?????? 16-20 km kal??nl??????ndaki tabaka.\n";
                        } else if (word.equals("hidrofil")) {
                            tanim = "Suyu ??eken, suyu emen.";
                        } else if (word.equals("komplike")) {
                            tanim = "????elerinin ya da gerekli i??lemlerinin say??s??n??n ??oklu??u, ??e??itlili??i" +
                                    " nedeniyle anla????lmas??, yap??lmas?? zor olan (??ey).";
                        } else if (word.equals("diksiyon")) {
                            tanim = "S??z?? kullanma, konu??ma eyleminin ????eler aras??ndaki ba??lant??lar??," +
                                    " duraklar??, vurgulamay??, titremlemeyi ilgilendiren b??l??m??.";
                        } else if (word.equals("metropol")) {
                            tanim = "???? i??e ge??mi?? b??y??k kentlerden ve banliy??lerden olu??an, ??evreye ve" +
                                    " ??lkeye g??re k??lt??r ve ekonomi y??n??nden en geli??mi?? olan merkez ??ehir.";
                        }

                        asteriks = new String(new char[word.length()]).replace("\0", "*");
                        yanit.setText(asteriks);
                        yanit.setTextColor(Color.BLACK);
                        tanimtv.setText("Tan??m: " + tanim);
                        soru += 1;
                        break;
                    case 2:
                        seviye.setText("4. Soru - 9 Harfli");
                        harfSayisi += 1;
                        tahmin.setVisibility(view.INVISIBLE);
                        tahminOnay.setVisibility(view.INVISIBLE);
                        tahminSuresi.setVisibility(view.INVISIBLE);
                        harfAl.setVisibility(view.VISIBLE);
                        tahminEt.setVisibility(view.VISIBLE);
                        zaman.setVisibility(view.VISIBLE);
                        sonraki.setVisibility(view.INVISIBLE);
                        String[] words3 = {"bocalamak", "yeltenmek", "deplasman", "imparator", "dolaylama"};

                        word = words3[(int) (Math.random() * words3.length)];
                        if (word.equals("bocalamak")) {
                            tanim = "Zor bir durum veya olay kar????s??nda ki??inin bir m??ddet teredd??t ve zorluk" +
                                    " ya??amas??n?? anlatan bir kelimedir.\n";
                        } else if (word.equals("yeltenmek")) {
                            tanim = "Alt??ndan kalkamayaca????, ba??aramayaca????, yapamayaca???? bir i??e kalk????mak.";
                        } else if (word.equals("deplasman")) {
                            tanim = "Bir spor tak??m??n??n ba??ka bir spor tak??m??n??n kentine ma?? yapmaya gitmesi," +
                                    " kar????la??may?? kar???? tak??m??n kentinde yapmas??.";
                        } else if (word.equals("imparator")) {
                            tanim = "Krallar??n da kendisine ba??l?? oldu??u en y??ksek h??k??mdar.";
                        } else if (word.equals("dolaylama")) {
                            tanim = "Bir tek s??zc??kle belirtilebilecek bir kavram?? bir??ok s??zc??kle anlatma.";
                        }

                        asteriks = new String(new char[word.length()]).replace("\0", "*");
                        yanit.setText(asteriks);
                        yanit.setTextColor(Color.BLACK);
                        tanimtv.setText("Tan??m: " + tanim);
                        soru += 1;
                        break;
                    case 3:
                        seviye.setText("5. Soru - 10 Harfli");
                        harfSayisi += 1;
                        tahmin.setVisibility(view.INVISIBLE);
                        tahminOnay.setVisibility(view.INVISIBLE);
                        tahminSuresi.setVisibility(view.INVISIBLE);
                        harfAl.setVisibility(view.VISIBLE);
                        tahminEt.setVisibility(view.VISIBLE);
                        zaman.setVisibility(view.VISIBLE);
                        sonraki.setVisibility(view.INVISIBLE);
                        String[] words4 = {"jeopolitik", "efkarlanma", "esrarengiz", "harikulade", "interaktif"};

                        word = words4[(int) (Math.random() * words4.length)];
                        if (word.equals("jeopolitik")) {
                            tanim = "????inde bulundu??u co??rafyan??n ve onun yan?? s??ra ekonominin, n??fusun" +
                                    " vb. bir devletin siyasas?? ??zerindeki etkisi.\n";
                        } else if (word.equals("efkarlanma")) {
                            tanim = "??z??nt??ye d????mek, tasalanmak, s??k??nt??lanmak.";
                        } else if (word.equals("esrarengiz")) {
                            tanim = "Kimsenin bilmedi??i gizli y??nleri bulunan, ne oldu??u belli olmayan, gizlerle dolu olan.";
                        } else if (word.equals("harikulade")) {
                            tanim = "E??i g??r??lmemi??, ??a??k??nl??k yarat??c??, ola??an??st??.";
                        } else if (word.equals("interaktif")) {
                            tanim = "Bir ileti??im terimidir ve birbirini kar????l??kl?? olarak etkileyerek ileti??ime ge??meyi ifade eder.";
                        }

                        asteriks = new String(new char[word.length()]).replace("\0", "*");
                        yanit.setText(asteriks);
                        yanit.setTextColor(Color.BLACK);
                        tanimtv.setText("Tan??m: " + tanim);
                        soru += 1;
                        break;
                    case 4:
                        cancelTimer = true;
                        win = true;
                        Intent i = new Intent(MainActivity2.this, gameOverActivity.class);
                        i.putExtra("skor1", String.valueOf(skor));
                        i.putExtra("sayac1", String.valueOf(sayac));
                        i.putExtra("win1", win);
                        startActivity(i);
                        finish();
                }
            }
        });


    }
}