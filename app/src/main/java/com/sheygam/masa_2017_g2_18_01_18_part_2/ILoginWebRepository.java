package com.sheygam.masa_2017_g2_18_01_18_part_2;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by gregorysheygam on 18/01/2018.
 */

public interface ILoginWebRepository {
    AuthToken login(Auth auth);
    AuthToken registration(Auth auth) throws Exception;
}
