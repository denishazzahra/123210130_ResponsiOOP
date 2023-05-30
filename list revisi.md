revisi:
1. interface
          -awalnya parameternya cuma String room, skrg ditambah sama int duration
3. model
          -ada salah di implementasi interface, awalnya method itu cuma ngambil harga dr database terus dikaliinnya diluar, sekarang udah didalem semua
          -kalo ada 2 user booking kamar yg sama (yg pertama blm bayar jd masih empty di tabel, bisa dibooking dr user) terus salah satunya bayar, satunya jadi gabisa bayar.
