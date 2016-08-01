# Aletheia
Aletheia is the library for creating and validating license files for Java applications. It is based on a set of 
application specific properties they are characteristic for the customer and purchased application's configuration.

# How to use

Add aletheia jar file to your application classpath. The following example shows the basic usage of this library.

Create new license and save it as „mylicense.lic“ file.

```java
// create simple lincense object
License license = new License();
license.setProduct("Aletheia");
license.setProductVersion("1.0.0");
license.setUuid(UUID.randomUUID().toString());
license.setCompany("PAWLIDI.DE");
license.setCreated(DateTime.now());
license.setDueDate(DateTime.now().plusMonths(2));
license.setDescription("License file for PAWLIDI.DE");

// create aletheia object
Aletheia aletheia = new Aletheia();

// save license as lic file
aletheia.saveLicense("mylicense.lic", license);

// dispose reserved fields
aletheia.dispose();
```

The lic file contains following informations.
```java
#
#Mon Aug 01 01:23:36 CEST 2016
signature=Skk7BDXldcv5kafyJEHX3Y+LdqRLTTiA8SHmm6oaL5Mzrm+kldrPnJLhy4thUni2Hsa7KaB/PYhxOULGMPSeC+rxtuWe61gpbL3Scwk2ji2LSOvWUk/V1p7ZEOKBA6dj4cirZxgmtP0/cSixYzbQ6iuy1AY8IFYqTlEYRcn61WaZaKiTaiCIgXO7Oa8UeHhhXjbZXZYpPRxCU/bqMfEz6PqWQn55/taoZIVcF4fIwQJY5WMdQ9Lv6AyQmdPNJpkZkZ+HVpTKxESxXxOZZQ7DgHSWvAdGkJzONGxPVSu0K/l5h/Rx9eyYpwOJdRi5ifYQ2a/+KasSULQohZoN4l9rjw\=\=
company=PAWLIDI.DE
version=1.0.0
description=License file for PAWLIDI.DE
due_date=10/01/2016
product=Aletheia
created=08/01/2016
```

Load exist license file
```java
// create aletheia object
Aletheia aletheia = new Aletheia("mylicense.lic");

// verify license
aletheia.verifyLicense();

// dispose reserved fields
aletheia.dispose();
```
##Dependencies
```xml
	<dependency>
  		<groupId>commons-lang</groupId>
  		<artifactId>commons-lang</artifactId>
  		<version>2.6</version>
  	</dependency>
  	<dependency>
  		<groupId>commons-validator</groupId>
  		<artifactId>commons-validator</artifactId>
  		<version>1.5.1</version>
  	</dependency>
  	<dependency>
  		<groupId>commons-io</groupId>
  		<artifactId>commons-io</artifactId>
  		<version>2.5</version>
  	</dependency>
  	<dependency>
  		<groupId>commons-codec</groupId>
  		<artifactId>commons-codec</artifactId>
  		<version>1.10</version>
  	</dependency>
  	<dependency>
  		<groupId>org.apache.commons</groupId>
  		<artifactId>commons-exec</artifactId>
  		<version>1.3</version>
  	</dependency>
  	<dependency>
  		<groupId>junit</groupId>
  		<artifactId>junit</artifactId>
  		<version>4.12</version>
  	</dependency>
  	<dependency>
  		<groupId>joda-time</groupId>
  		<artifactId>joda-time</artifactId>
  		<version>2.9.4</version>
  	</dependency>
  	<dependency>
  		<groupId>org.jasypt</groupId>
  		<artifactId>jasypt</artifactId>
  		<version>1.9.2</version>
  	</dependency>
  	<dependency>
  		<groupId>commons-net</groupId>
  		<artifactId>commons-net</artifactId>
  		<version>3.5</version>
  	</dependency>
```