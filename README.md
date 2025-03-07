### Advance programming

- Nama    : Akhyar Rasyid Asy syifa
- NPM     : 2306241682
- Kelas   : Adpro - A

### archive reflection🧑‍💻

<details>
<summary>📒 Module 1</summary>

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
</details>

<details>
<summary>📒 Module 2</summary>

# Module 2: CI/CD & DevOps

## Reflection
> 1. List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.

- Di awal, saya melakukan _refactor_ nama file seperti `CreateProduct.html` menjadi `createProduct.html`, `HomePage.html` menjadi `homePage.html`, dan seterusnya. Tujuannya agar lebih konsisten dalam penggunaan camel case di seluruh project.
- Menambahkan izin yang sesuai untuk nge allow `./gradlew` nya buat di run di dalam `ci.yml` dengan tambahan berikut: 
    ```yaml 
    - name: Add gradlew permission
      run: chmod +x gradlew
    ```
- Memperbaiki bagian upload artifact sesuai arahan asdos, karena versi 3 (v3) sudah tidak support di banyak kasus. dari yang awalnya:
    ```yaml
    uses: actions/upload-artifact@97a0fba1372883ab732affbe8f94b823f91727db # v3.pre.node20 
    ```
    Saya menggantinya menjadi:
    ```yaml
    uses: actions/upload-artifact@v4
    ```
- Memperbaiki nama method yang sebelumnya kurang sesuai dengan Java Naming Convention, misalnya dari get_product_list() menjadi getProductList(). Ini membantu meningkatkan keterbacaan kode dan mempermudah maintenance di masa depan.
- Di Java, method dalam interface sudah public secara default, jadi saya menghapus modifier public dari method-method di `ProductService.java` agar lebih clean dan tidak redundan.
- Saya juga mengupdate PMD action di GitHub Workflow ke ```pmd/pmd-github-action@v2``` agar menggunakan versi yang lebih baru dan stabil.
- Di beberapa method, saya juga mencoba membersihkan import wildcard untuk mencegah potensi konflik penamaan dan ambiguitas.

> 2. Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!

Menurut saya, proyek ini sudah memenuhi definisi CI/CD. Di sisi Continuous Integration, saya menggunakan GitHub Actions dengan workflow yang saya definisikan di dalam folder .github/workflows. Alur kerja ini secara otomatis akan terpicu setiap kali ada _push_ atau _pull_ request ke branch mana pun. Di dalam `ci.yml`, saya juga membuat proses otomatis untuk build dan unit testing. Untuk memastikan kualitas dan keamanan kode tetap terjaga, saya juga mengintegrasikan tools tambahan seperti `Scorecard` dan `PMD` yang akan mengevaluasi potensi masalah keamanan setiap ada perubahan kode. Menurut saya ini penting untuk mendeteksi masalah sejak dini sebelum kode masuk ke _production_.

Di sisi Continuous Deployment, saya memilih menggunakan **Koyeb** sebagai _platform deployment_. Setiap kali ada _push_ atau _pull_ request ke branch main, Koyeb akan secara otomatis mengambil perubahan tersebut dan men-deploy versi terbaru dari aplikasi web saya. Proses otomatis ini sangat membantu karena saya tidak perlu melakukan _deployment_ manual, yang tentunya bisa memakan waktu dan rawan human error.  Hal ini juga bisa membuat saya bisa fokus pada pengembangan fitur tanpa terlalu khawatir dengan proses technical yang repetitif.

</details>

<details>
<summary>📒 Module 3</summary>
    
# Module 3: Maintainability & OO Principles

## Reflection
### 1. List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.
Dalam tutorial modul ini, saya menerapkan prinsip SOLID untuk memastikan bahwa kode lebih terstruktur, mudah dipelihara, dan fleksibel untuk dikembangkan di masa depan. Berikut adalah prinsip-prinsip yang saya gunakan:

**Single Responsibility Principle (SRP)**.
Saya memisahkan CarController dari `ProductController` karena masing-masing memiliki tanggung jawab berbeda. `CarController` hanya mengelola car, sementara `ProductController` mengelola produk in general. Selain itu, saya juga memisahkan logika ID generation ke dalam method `generateId()` di repository agar lebih terorganisir.

**Open-Closed Principle (OCP)**.
Jika ada perubahan pada Car, tidak akan memengaruhi Product. Lalu jika ada fitur baru untuk Car, cukup modifikasi `CarController` saja tanpa menyentuh `ProductController`.

**Liskov Substitution Principle (LSP)**.
Saya memisahkan `CarController` dari `ProductController`. Sebelumnya, `CarController` merupakan subclass dari `ProductController`, padahal keduanya memiliki tanggung jawab yang berbeda. Hal ini bertentangan dengan LSP karena CarController tidak sepenuhnya bisa menggantikan ProductController tanpa mengubah perilaku yang diharapkan. 

**Interface Segregation Principle (ISP)**. 
Saya memisahkan `CarService` sama `ProductService`, di mana masing-masing memiliki tanggung jawabnya sendiri dalam mengelola operasi CRUD (Create, Read, Update, Delete). Kedua service ini berdiri sendiri tanpa saling bergantung, sehingga perubahan pada satu service tidak akan memengaruhi yang lain. 

**Dependency Inversion Principle (DIP)**.
Saya mengganti dependensi `CarController` dari `CarServiceImpl` menjadi `CarService`. Dengan cara ini, `CarController`tidak bergantung pada implementasi konkret, tapi pada interface yang lebih fleksibel. 

### 2. Explain the advantages of applying SOLID principles to your project with examples.
Dengan menerapkan SOLID, code bakal menjadi lebih terstruktur, mudah dikelola, dan fleksibel untuk dikembangkan. Dalam case saya misal, dengan memisahkan `CarController` dari `ProductController`, setiap perubahan atau penambahan fitur pada car tidak akan memengaruhi produk lain. Selain itu, code yang lebih modular dan memiliki tanggung jawab tunggal membuatnya lebih mudah dibaca, diuji, serta meminimalkan risiko bug akibat perubahan di satu bagian code yang dapat berdampak ke bagian lain.

### 3. Explain the disadvantages of not applying SOLID principles to your project with examples.

Tanpa menerapkan prinsip SOLID, saya akan menghadapi banyak kesulitan dalam mengelola code dan meningkatkan risiko error. Jika `CarController` tetap bergantung pada `ProductController`, setiap perubahan kecil pada produk bisa berdampak pada mobil, yang justru menghambat pengembangan. Selain itu, tanpa abstraksi seperti `CarService`, setiap kali saya mengubah implementasi service, saya harus menyesuaikan banyak bagian code lain, yang membuat segalanya semakin kompleks dan rentan terhadap bug. Kurangnya modularitas juga membuat pengujian menjadi sulit, karena saya tidak bisa melakukan unittest tanpa bergantung pada implementasi konkret. Akibatnya, proyek saya akan menjadi sulit diperluas, tidak fleksibel terhadap perubahan, dan bakal jauh lebih sulit dikelola dalam jangka panjang.

</details>

# Module 4: Test-Driven Development & Refactoring

## Reflection
> 1. Reflect based on Percival (2017) proposed self-reflective questions (in “Principles and Best Practice of Testing” submodule, chapter “Evaluating Your Testing Objectives”), whether this TDD flow is useful enough for you or not. If not, explain things that you need to do next time you make more tests.

Menerapkan TDD dalam pengembangan kode sangat membantu saya dalam memahami kegunaan sebuah kode serta mendefinisikan perilaku yang diharapkan dengan lebih jelas. Dengan menulis pengujian terlebih dahulu, saya jadi lebih mudah memastikan bahwa setiap bagian kode memiliki tujuan yang spesifik. Selain itu, ketika setelah pengimplementasian sebuah class masih ada pengujian yang gagal, saya justru merasa bahwa TDD bekerja sebagaimana mestinya, karena kegagalan tersebut menunjukkan bagian kode yang masih perlu diperbaiki. Namun, saya juga mengalami beberapa tantangan dalam menerapkan TDD. Salah satunya adalah ada beberapa _case_ yang terlewat dalam pengujian saat saya mengimplementasikan suatu class. Hal ini membuat saya sadar bahwa cakupan testcase awal saya ternyata masih perlu diperluas agar lebih menyeluruh. Selain itu, karena saya masih cukup baru dalam menggunakan TDD dan belum banyak terbiasa dengan unit testing sebelumnya, saya sering kesulitan dalam merancang desain pengujian yang baik.

> 2. You have created unit tests in Tutorial. Now reflect whether your tests have successfully followed F.I.R.S.T. principle or not. If not, explain things that you need to do the next time you create more tests.

Dalam kode saya beberapa prinsip dari F.I.R.S.T. sudah saya implementasikan dengan baik dan sebagian belum dapat saya implementasikan. 
- **Fast (F)**.
  sudah saya implementasikan karena dalam test saya, saya hanya mengandalkan memori dan tidak menggunakan dependencies eksternal yang dapat memperlambat jalannya program.
- **Independent (I)**.
  berhasil saya implementasikan via `@BeforeEach` yang membuat satu tests dengan tests lain bersih dan tidak tergantung satu sama lain.
- **Repeatable (R)**.
  saya implementasikan dengan berhasil menjalankan tests baik di lokal maupun di bagian CI (Continuous Integration).
- **Self-Validating**.
  saya implementasikan melalui test-test assertion (`assertThrows`, `assertNull`, `assertEquals`) yang melakukan validasi ulang beberapa hal.
- **Timely (T)**.
  Terakhir, Timely (T) saya implementasikan melalui pembuatan testing yang saya lakukan terlebih dahulu lalu mengimplementasikan class nya. Lalu tests saya juga sudah meng-cover semua kemungkinan error, results, happy paths, dan unhappy paths.

_Overall_, saya merasa sudah cukup baik dalam menerapkan prinsip F.I.R.S.T. dalam unit testing yang saya buat. Saya berusaha memastikan bahwa setiap pengujian berjalan secara independen dan hasilnya yang dapat diandalkan buat bikin class nantinya. Namun, ada beberapa hal yang masih perlu saya tingkatkan agar penerapan prinsip ini lebih optimal. Salah satu hal yang perlu saya perbaiki adalah memastikan bahwa unit testing yang saya buat benar-benar mencakup seluruh kode, termasuk berbagai edge-case yang mungkin terjadi.
