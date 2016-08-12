/*
 * Copyright (C) 2016 Maximilian Pawlidi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.pawlidi.openaletheia;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * 
 * @author PAWLIDIM
 * 
 */
public abstract class Constants {

	/** Define the software version */
	public static final String ALETHEIA_VERSION = "1.0.0";

	/** Define the private key */
	public static final String ALETHEIA_PRIVATE_KEY = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCto5CpWlbTBpGs3l2yuirXIZUextLE4A3WKwxyHvaaGwhoX8Yj+aCS+JP5W6aQIN3kRGxI0Q4MjxNpJQlkEyfiANStlpaSAPed5JUosHLKXAHuS6F62ilLO9S816goeQU96mmOwHFjg80DWtJp53uIhugFq7Xd86BzZeoe+FyToB0gArgGDI7q1dKTDA9ACrPTp/7f7cWgt9xGJV61QEYCDTeCcuP/o90CbrK9p6yeZUReS66/VIdQitTokFzDk9ImrF2xJHtjfKBTv/y2qW1WV6Znd4TOb8cdn9pd1QEJB0wnZUNt+IYsLeE/oFuLdTBQFg6ADfqhTVReuCMhC0nFAgMBAAECggEAM5J2H+amDQ1RbR+qgrAKk1T1Hmv9I95MHcUxcB/ELKdEywNPLVYyHBTalmBjS0GHxgmQg9wbqR1BraQDcwbBfL20bU5hsEG7SdkfeDNYFMRZCnbKIlvzkdXWbLQ8zLCNHwOlkHGV+/GkjY+HGU9YgIdZGH4UiTSX/k5RyBsKHAofe/vGW8lMpErfIc3XzSZOKntIDLWqpLlwR5hqIpJuX0plhTgpscbJYNPBeT6g/76q5LJcHJQbWtvlg6dc6C+aXJ5TIbS+/1WTm66mrHGuyR+M6OlroFDv3yrRjZy/yr8lpYQoezpI+SP0nKqTLza2gUAdY80Ezpqk1I9EhLsEgQKBgQDmvqXBTWiQt4tWJU6YawBmxqRC34tKFo1i16oKQN9USE0Ah508BLDHL9TGI+99VVkF62azb81DtRbYlCgSBGrHNwvNxu6OJEu1Qgdgd1acBH3+pw3Ue60uUA/Svp+HIl6/WbOZ4NTzTUIXu1GyviJi/QEzioCT/b17msh3mC2SJQKBgQDApNd353PP8An75nlbLI0NWKUPh12PJNfhg7nYEKC+kZaXfiw493U/JpZDmYVWK5r1gQrf49NITYnWzOSK/HDpAF+ijPPolw0AW81HwkV6i+/110rravcYsJffNsDT05orf2dhg4cu0cHL9HNGpyHGmrzPBLliwD11RO2J1ve3IQKBgQDkL2Dn2k8v3fLzDA2/eQF6obJLV5n9eHP1bWUAosq4JI/RmEsL0W+oa0q1b1Ak18QjsHlttYB+FpUzNMCzoJ6fx3Xq13GOK6Ka62M0KJAD1gVrUXTBzlSi2hXryQslrf2YqXKNF+Q/fwV/RoUyiAilb6JCgBNLRsCdfaIFcv6N6QKBgQCrhhoTOJF1Y1SAZQVImLI+X98XpH85JKTStmaSaru08gwTdtQJJMvQHptMNTTzNIAUd4EnN8K3bdJ83pHe7kXaL+r24GmLfnKlDxgH/IHASzisu7SJSAQah6GxrR6jfJSYhieWO3XxyfGzl0PMdKMuIjhaWup1cpVGBMk7IZwOAQKBgQCnd9hLJzAfpv5kgMThUhs5qth/9aK+kouvTpOvu/LULk1Rpn1sd/c+pRzfKKDYaYmyEO2UWQexuBzJQfr8UUPbv6371RRdQUKHPzFcViXIJFTyS/6LTaaQHfqiA1jVEz0Giaa19UlZFi+YFEIEIArYUYXGG6CjUjdXxGjVrc7aHA==";
	/** Define the public key */
	public static final String ALETHEIA_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAraOQqVpW0waRrN5dsroq1yGVHsbSxOAN1isMch72mhsIaF/GI/mgkviT+VumkCDd5ERsSNEODI8TaSUJZBMn4gDUrZaWkgD3neSVKLByylwB7kuhetopSzvUvNeoKHkFPeppjsBxY4PNA1rSaed7iIboBau13fOgc2XqHvhck6AdIAK4BgyO6tXSkwwPQAqz06f+3+3FoLfcRiVetUBGAg03gnLj/6PdAm6yvaesnmVEXkuuv1SHUIrU6JBcw5PSJqxdsSR7Y3ygU7/8tqltVlemZ3eEzm/HHZ/aXdUBCQdMJ2VDbfiGLC3hP6Bbi3UwUBYOgA36oU1UXrgjIQtJxQIDAQAB";

	/**
	 * Defines an instance of SimpleDateFormat used for formatting the string
	 * representation of date (month/day/year)
	 */
	public static final DateTimeFormatter DATE_FORMAT = DateTimeFormat.forPattern("MM/dd/yyyy");
}
