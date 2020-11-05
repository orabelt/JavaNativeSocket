# JavaNativeSocket
JNI Local Socket implementations

# Sample program output
```
C:\Jdk\jdk1.8.0-openjdk-1.8.0.252-2.b09.redhat.windows.x86\bin\java.exe  javacli.Test

impl: java.net.Socket
41097 requests/sec.
47790 requests/sec.
48270 requests/sec.
48490 requests/sec.
49133 requests/sec.
49458 requests/sec.
Process finished with exit code 0

impl: javacli.LocalSocket
34693 requests/sec.
38253 requests/sec.
36416 requests/sec.
37904 requests/sec.
36830 requests/sec.
35984 requests/sec.
36283 requests/sec.
39131 requests/sec.
Process finished with exit code 0


C:\Jdk\zulu15.27.17-ca-jdk15.0.0-win_i686\bin\java.exe  javacli.Test
impl: java.net.Socket
45864 requests/sec.
47177 requests/sec.
47542 requests/sec.
47692 requests/sec.
47258 requests/sec.
46676 requests/sec.
Process finished with exit code 0

impl: javacli.LocalSocket
41386 requests/sec.
43158 requests/sec.
42972 requests/sec.
43357 requests/sec.
43288 requests/sec.
43315 requests/sec.
Process finished with exit code 0
```