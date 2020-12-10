package com.jachs.jpa.nto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jachs.jpa.dao.nto.PlayerRepository;
import com.jachs.jpa.dao.nto.VideoGameRepository;
import com.jachs.jpa.entity.nto.Player;
import com.jachs.jpa.entity.nto.VideoGame;

/**
 * @author zhanchaohan
 * 
 */
@SpringBootTest
public class VideoGameTest {
    @Autowired
    private VideoGameRepository videoGameRepository;
    @Autowired
    private PlayerRepository playerRepository;
    
    //单条添加
    @Test
    public void testAddOne () {
        Player p1 = new Player ();
        p1.setLevel ( 2 );
        p1.setPlayerUserName ( "新手玩家" );

        playerRepository.save ( p1 );
        
        VideoGame vg = new VideoGame ();
        vg.setGameName ( "" );
        vg.setGamePrice ( 5412 );

       
        vg.setPlayer ( p1 );
        videoGameRepository.save ( vg );
    }
    @Test
    public void testAddMany() {
        Player p1 = new Player ();
        p1.setLevel ( 3 );
        p1.setPlayerUserName ( "Level3玩家" );

        playerRepository.save ( p1 );
        
        VideoGame vg = new VideoGame ();
        vg.setGameName ( "LOL" );
        vg.setGamePrice ( 5412 );

        VideoGame vg1 = new VideoGame ();
        vg1.setGameName ( "OverWatch" );
        vg1.setGamePrice ( 2754 );
        
        vg.setPlayer ( p1 );
        vg1.setPlayer ( p1 );
        videoGameRepository.save ( vg );
        videoGameRepository.save ( vg1 );
    }
}
