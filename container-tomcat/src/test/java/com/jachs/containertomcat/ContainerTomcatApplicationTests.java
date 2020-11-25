package com.jachs.containertomcat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class ContainerTomcatApplicationTests {

	@Test
	void contextLoads() {
	    log.trace ( "SSSSSS" );
	    log.info ( "AAAAAAA" );
	    log.debug ( "BBBBBB" );
	    log.warn ( "CCCCCC" );
	    log.error ( "DDDDD" );
	    
	}

}
