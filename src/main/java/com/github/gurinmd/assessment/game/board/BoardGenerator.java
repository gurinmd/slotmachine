package com.github.gurinmd.assessment.game.board;

import com.github.gurinmd.assessment.game.config.data.Configuration;

public interface BoardGenerator {
  Board generate(Configuration configuration);
  
}
