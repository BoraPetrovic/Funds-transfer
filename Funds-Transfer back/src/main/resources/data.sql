INSERT INTO banka (id, naziv, sredstva_banke) VALUES (1, 'Intesa', 100);
INSERT INTO banka (id, naziv, sredstva_banke) VALUES (2, 'Erste', 50);

INSERT INTO tip_racuna (id, naziv, provizija, banka_id) VALUES (1,'Gold', 3, 1);
INSERT INTO tip_racuna (id, naziv, provizija, banka_id) VALUES (2,'Standard', 6, 2);
INSERT INTO tip_racuna (id, naziv, provizija, banka_id) VALUES (3,'Premium', 2, 2);
INSERT INTO tip_racuna (id, naziv, provizija, banka_id) VALUES (4,'Silver', 5, 1);

INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (1,'miroslav@maildrop.cc','miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','Miroslav','Simic','ADMIN');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (2,'tamara@maildrop.cc','tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','Tamara','Milosavljevic','KORISNIK');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (3,'petar@maildrop.cc','petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','Petar','Jovic','KORISNIK');


INSERT INTO racun (id, ime_prezime, jmbg, br_racuna, stanje, banka_id, tip_racuna_id) VALUES (1,'Sandra Djurovic','0108988303228', '895-897456-52', 1000000, 1, 1);
INSERT INTO racun (id, ime_prezime, jmbg, br_racuna, stanje, banka_id, tip_racuna_id) VALUES (2,'Biljana Djurovic','0108988303741', '895-147852-52', 100000, 1, 3);
INSERT INTO racun (id, ime_prezime, jmbg, br_racuna, stanje, banka_id, tip_racuna_id) VALUES (3,'Aleksandar Djurovic','0108988303789', '895-963258-52', 200000, 2, 1);
INSERT INTO racun (id, ime_prezime, jmbg, br_racuna, stanje, banka_id, tip_racuna_id) VALUES (4,'Milka Djurovic','0108988303963', '895-878521-52', 530000, 2, 2);
INSERT INTO racun (id, ime_prezime, jmbg, br_racuna, stanje, banka_id, tip_racuna_id) VALUES (5,'Milan Djurovic','0108988303357', '895-756354-52', 80000, 1, 4);
--INSERT INTO zadatak (id, ime, zaduzeni, bodovi, sprint_id, stanje_id) VALUES (2,'2 Kreirati pocetnu stranicu','Bane', 5, 1, 2);
--INSERT INTO zadatak (id, ime, zaduzeni, bodovi, sprint_id, stanje_id) VALUES (3,'3 Kreirati logo', 'Ana', 8, 2, 3);
--INSERT INTO zadatak (id, ime, zaduzeni, bodovi, sprint_id, stanje_id) VALUES (4,'4 Kreirati rest servis','Nikola', 8, 1, 1);
--INSERT INTO zadatak (id, ime, zaduzeni, bodovi, sprint_id, stanje_id) VALUES (5,'5 Kreirati pocetnu stranicu','Bane', 5, 1, 2);
--INSERT INTO zadatak (id, ime, zaduzeni, bodovi, sprint_id, stanje_id) VALUES (6,'6 Kreirati logo', 'Ana', 8, 2, 3);
--INSERT INTO zadatak (id, ime, zaduzeni, bodovi, sprint_id, stanje_id) VALUES (7,'7 Kreirati rest servis','Nikola', 8, 1, 1);
--INSERT INTO zadatak (id, ime, zaduzeni, bodovi, sprint_id, stanje_id) VALUES (8,'8 Kreirati pocetnu stranicu','Bane', 5, 1, 2);
--INSERT INTO zadatak (id, ime, zaduzeni, bodovi, sprint_id, stanje_id) VALUES (9,'9 Kreirati logo', 'Ana', 8, 2, 3);

