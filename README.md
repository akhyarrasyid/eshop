- Nama    : Akhyar Rasyid Asy syifa
- NPM     : 2306241682
- Kelas   : Adpro - A

# Module 1: Coding Standard

## Reflection 1
> You already implemented two new features using Spring Boot. Check again your source code and evaluate the coding standards that you have learned in this module. Write clean code principles and secure coding practices that have been applied to your code.  If you find any mistake in your source code, please explain how to improve your code

### Clean Code
Coding standard yang saya implementasikan adalah sebagai berikut:
- **Meaningful names** <br>
saya selalu menggunakan nama variabel, fungsi, kelas, dan argumen yang deskriptif dan tidak ambigu. Nama yang deskriptif diperlukan untuk menghindari kebingungan. Saya memastikan bahwa tidak ada komentar yang diperlukan untuk mendeskripsikan variabel, fungsi, atau kelas saya. berikut contohnya :
- **Function Do One Thing** <br>
saya telah memastikan bahwa setiap fungsi hanya melakukan satu hal dan melakukannya dengan baik. Sebagai contoh, dalam kelas `ProductService`, saya telah membuat fungsi terpisah untuk membuat, mengedit, dan menghapus produk.
- **Comments** <br>
di tutorial ini saya menghindari penggunaan komentar untuk hal-hal yang sudah jelas. Untuk modul khusus ini, saya tidak menulis komentar karena tidak ada logika atau algoritma yang rumit yang perlu saya jelaskan. 
- **Error Handling** <br>
Menggunakan beberapa input validation seperti pada input nama produk yang tidak boleh null atau kosong dan input kuantitas produk yang tidak boleh nol atau bernilai negatif. saya juga menyadari bahwa input validation untuk memastikan nama produk tidak kosong atau null, serta kuantitas produk tidak bernilai nol atau negatif, belum diterapkan. Oleh karena itu, saya sempat menambahkan validasi input tersebut dengan dependencies yang disediakan spring boot untuk mencegah adanya data yang tidak , yakni ```implementation("org.springframework.boot:spring-boot-starter-validation")```.
- **Objects and Data Structures** <br>
Untuk mengikuti prinsip-prinsip OOP, saya memastikan bahwa tidak ada ketergantungan yang tidak perlu antar variabel dengan membuat beberapa variabel menjadi private. Untuk modul ini, tidak banyak struktur data yang bisa saya implementasikan kecuali List dan Iterator. 

### Secure Coding
- Validasi input pada fields productName agar terhindar dari serangan sejenis SQL Injection dan XSS (Cross-Site Scripting).
- Mengunakan UUID untuk product untuk memastikan ID produk unik

Dalam source code saya, saya sempat menemukan beberapa masalah, salah satunya adalah tidak adanya ID Produk yang dihasilkan secara otomatis. Akibatnya, sebuah produk tidak bisa diidentifikasi secara unik dalam daftar produk. Untuk mengatasi hal ini, saya memutuskan untuk menggunakan `UUID` guna menghasilkan ID Produk secara otomatis setiap kali produk baru dibuat. Lalu, kedepannya Pada suatu logika kode yang kompleks, sebaiknya diberikan komentar tambahan untuk menjelaskan logika yang sebenarnya 


## Reflection 2
> 1. After writing the unit test, how do you feel? How many unit tests should be made in a class? How to make sure that our unit tests are enough to verify our program? It would be good if you learned about code coverage. Code coverage is a metric that can help you understand how much of your source is tested. If you have 100% code coverage, does that mean your code has no bugs or errors?

Setelah menulis unit test, saya menjadi lebih percaya dengan kualitas dan kebenaran kode yang saya miliki. Selain itu, saya semakin menyadari seberapa pentingnya unit test dalam pemrograman skala besar nantinya ketika sudah bekerja.
Dalam sebuah class, jumlah unit test tergantung dari function yang dimiliki oleh setiap class. Setiap function tersebut minimal memiliki 1 unit test yang bertanggungjawab menjalankan fitur penting yang dimiliki oleh function tersebut. Lalu selebihnya bisa diuji yang mencakup berbagai skenario, terutama kasus-kasus edge case yang mungkin terjadi. jadi ketika ditanya berapa banyak unit test yang perlu diimplementasikan, jawabannya adalah bervariasi dan tergantung seberapa kompleks class yang didefine. 

Selama mengerjakan latihan unit test ini, saya belajar bahwa meskipun code coverage berguna, itu tidak menjamin code kita itu bebas bug. Misalnya, meskipun pengujian pembaruan produk saya sudah mencakup 100% kode, saya awalnya melewatkan validasi untuk nama produk yang hanya berisi spasi. Tapi gapapa, justru ini mengajarkan ke saya bahwa code coverage hanyalah alat bantu untuk memastikan kualitas kode, bukan tujuan akhir.

>2. Suppose that after writing the CreateProductFunctionalTest.java along with the corresponding test case, you were asked to create another functional test suite that verifies the number of items in the product list. You decided to create a new Java class similar to the prior functional test suites with the same setup procedures and instance variables. <br>
What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce the code quality? Identify the potential clean code issues, explain the reasons, and suggest possible improvements to make the code cleaner! 

Setelah menulis rangkaian tes functional yang baru, saya mulai melihat beberapa masalah di kode pengujian saya. yang paling jelas saya sadari adalah duplikasi kode, karena saya menyalin prosedur penyiapan dan variabel contoh yang sama dari `CreateProductFunctionalTest`.java ke kelas uji baru. Ini jelas bertentangan dengan prinsip DRY (Don't Repeat Yourself). Misalnya, jika saya perlu mengubah cara pengaturan (seperti beralih dari Chrome ke Firefox), saya harus melakukannya di beberapa tempat. Hal ini tentu saja membuat pemeliharaan jadi lebih sulit dan meningkatkan kemungkinan terjadinya inkonsistensi.

Membuat kelas Java yang sama dengan rangkaian tes fungsional utama bisa menurunkan kebersihan kode karena adanya duplikasi dan redundansi. Untuk mengatasinya, saya bisa membuat superclass yang berisi set up umum, lalu menggunakannya di dalam subclass yang membutuhkan pengaturan tersebut. Dengan cara ini, duplikasi dan redundansi kode yang bisa merusak kebersihan kode bisa dihindari.







