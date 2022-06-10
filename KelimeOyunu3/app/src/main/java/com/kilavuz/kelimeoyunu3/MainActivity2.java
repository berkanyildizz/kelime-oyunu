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


                zaman.setText("Kalan Süre: " + sayac--);
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
            tanim = "Bir işyerinde temizlik ve ayak işlerine bakan kimse, odacı.\n";
        } else if (word.equals("adalet")) {
            tanim = "Hak ve hukuka uygunluk; hak ve hukuku gözetme ve yerine getirme; doğruluk.";
        } else if (word.equals("icraat")) {
            tanim = "Yapılan işler, çalışmalar, uygulamalar.";
        } else if (word.equals("kadraj")) {
            tanim = "Fotoğraf makinasının ve kameranın vizöründe görünen görüntüdür.";
        } else if (word.equals("patika")) {
            tanim = "Engebeli bir kırsal arazide iki nokta arasındaki en kısa yolun insan ve" +
                    " hayvan geçişleri nedeniyle bitkiden arınarak oluşması.";
        }

        asteriks = new String(new char[word.length()]).replace("\0", "*");
        yanit.setText(asteriks);
        tanimtv.setText("Tanım: " + tanim);
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

                        tahminSuresi.setText("TAHMİN SÜRESİ: " + sayac2--);
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
                        tahminSuresi.setText("SÜRE DOLDU!");
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
                            yanlisCevap.setText("YANLIŞ CEVAP!!");
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


                        zaman.setText("Kalan Süre: " + sayac--);
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
                            tanim = "Bir filmin başındaki veya sonundaki tanıtım yazıları.\n";
                        } else if (word.equals("taarruz")) {
                            tanim = "Kötülük yapmak, yıpratmak amacıyla, bir kimseye karşı doğrudan" +
                                    " doğruya silahlı veya silahsız bir eylemde bulunma, hücum.";
                        } else if (word.equals("serenat")) {
                            tanim = "Geceleyin, açık havada sevgi duyulan biri için bir müzik aracıyla verilen küçük konser.";
                        } else if (word.equals("teselli")) {
                            tanim = "Acı bir olayı unutturmaya, acısını hafifletmeye çalışma, avuntu, avunç.";
                        } else if (word.equals("meziyet")) {
                            tanim = "Bir nesneyi ya da kişiyi benzerinden üstün gösteren nitelik.";
                        }

                        asteriks = new String(new char[word.length()]).replace("\0", "*");
                        yanit.setText(asteriks);
                        yanit.setTextColor(Color.BLACK);
                        tanimtv.setText("Tanım: " + tanim);
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
                            tanim = "Dünya'da canlıların yaşadığı 16-20 km kalınlığındaki tabaka.\n";
                        } else if (word.equals("hidrofil")) {
                            tanim = "Suyu çeken, suyu emen.";
                        } else if (word.equals("komplike")) {
                            tanim = "Öğelerinin ya da gerekli işlemlerinin sayısının çokluğu, çeşitliliği" +
                                    " nedeniyle anlaşılması, yapılması zor olan (şey).";
                        } else if (word.equals("diksiyon")) {
                            tanim = "Sözü kullanma, konuşma eyleminin öğeler arasındaki bağlantıları," +
                                    " durakları, vurgulamayı, titremlemeyi ilgilendiren bölümü.";
                        } else if (word.equals("metropol")) {
                            tanim = "İç içe geçmiş büyük kentlerden ve banliyölerden oluşan, çevreye ve" +
                                    " ülkeye göre kültür ve ekonomi yönünden en gelişmiş olan merkez şehir.";
                        }

                        asteriks = new String(new char[word.length()]).replace("\0", "*");
                        yanit.setText(asteriks);
                        yanit.setTextColor(Color.BLACK);
                        tanimtv.setText("Tanım: " + tanim);
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
                            tanim = "Zor bir durum veya olay karşısında kişinin bir müddet tereddüt ve zorluk" +
                                    " yaşamasını anlatan bir kelimedir.\n";
                        } else if (word.equals("yeltenmek")) {
                            tanim = "Altından kalkamayacağı, başaramayacağı, yapamayacağı bir işe kalkışmak.";
                        } else if (word.equals("deplasman")) {
                            tanim = "Bir spor takımının başka bir spor takımının kentine maç yapmaya gitmesi," +
                                    " karşılaşmayı karşı takımın kentinde yapması.";
                        } else if (word.equals("imparator")) {
                            tanim = "Kralların da kendisine bağlı olduğu en yüksek hükümdar.";
                        } else if (word.equals("dolaylama")) {
                            tanim = "Bir tek sözcükle belirtilebilecek bir kavramı birçok sözcükle anlatma.";
                        }

                        asteriks = new String(new char[word.length()]).replace("\0", "*");
                        yanit.setText(asteriks);
                        yanit.setTextColor(Color.BLACK);
                        tanimtv.setText("Tanım: " + tanim);
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
                            tanim = "İçinde bulunduğu coğrafyanın ve onun yanı sıra ekonominin, nüfusun" +
                                    " vb. bir devletin siyasası üzerindeki etkisi.\n";
                        } else if (word.equals("efkarlanma")) {
                            tanim = "Üzüntüye düşmek, tasalanmak, sıkıntılanmak.";
                        } else if (word.equals("esrarengiz")) {
                            tanim = "Kimsenin bilmediği gizli yönleri bulunan, ne olduğu belli olmayan, gizlerle dolu olan.";
                        } else if (word.equals("harikulade")) {
                            tanim = "Eşi görülmemiş, şaşkınlık yaratıcı, olağanüstü.";
                        } else if (word.equals("interaktif")) {
                            tanim = "Bir iletişim terimidir ve birbirini karşılıklı olarak etkileyerek iletişime geçmeyi ifade eder.";
                        }

                        asteriks = new String(new char[word.length()]).replace("\0", "*");
                        yanit.setText(asteriks);
                        yanit.setTextColor(Color.BLACK);
                        tanimtv.setText("Tanım: " + tanim);
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