package com.jachs.jpa.nto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jachs.jpa.dao.nto.VideoGameRepository;
import com.jachs.jpa.entity.nto.VideoGame;
/**
 * @author zhanchaohan
 * 
 */
@SpringBootTest
public class VideoGameTest {
    @Autowired
    private VideoGameRepository VideoGameRepository;
    
    @Test
    public void testAddOne() {
        VideoGame vg=new VideoGame();
        vg.setGameName ( "" );
        vg.setGamePrice ( 5412 );
        
//        vg.setPlayer ( Player );
    }
}
