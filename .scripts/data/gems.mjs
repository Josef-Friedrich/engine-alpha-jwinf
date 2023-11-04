// https://jwinf.de/tasks/jwinf/jwinf-aufgaben/2017/17-FR-04-collecting-gems-2/task_new.js
const subTask = {};

export default subTask;
// var cellSide = 60;

subTask.gridInfos = {
  hideSaveOrLoad: true,
  // cellSide: cellSide,
  conceptViewer: false,
  contextType: "gems",
  actionDelay: 200,
  // itemTypes: {
  //    green_robot: { img: "green_robot.png", side: 80, nbStates: 9, isObstacle: true, dir: 1, offsetX: -14, category: "robot", team: 0, zOrder: 2 },
  //    obstacle: { num: 2, img: "obstacle.png", side: cellSide, category: "obstacle", isObstacle: true, isHole: false, zOrder: 0 },
  //    gem: { num: 3, img: "blue_gem.png", side: cellSide, category: "marble", isObstacle: false, isCollectible: true, zOrder: 1 },
  // },

  // https://jwinf.de/tasks/jwinf/_common/modules//pemFioi/blocklyRobot_lib-1.1.js

  backgroundColor: "#BF5E47",
  borderColor: "#96413B",
  itemTypes: {
    robot: {
      img: "yellow_robot.png",
      side: 80,
      nbStates: 9,
      isRobot: true,
      offsetX: -11,
      zOrder: 2,
    },
    gem: {
      num: 3,
      img: "gem.png",
      side: 60,
      isWithdrawable: true,
      autoWithdraw: true,
      zOrder: 1,
    },
    obstacle: {
      num: 4,
      img: "brick_wall.png",
      side: 60,
      isObstacle: true,
      zOrder: 0,
    },
    number: { num: 5, side: 60, zOrder: 1 },
  },
  maxInstructions: {
    easy: 8,
    medium: 20,
    hard: 15,
  },
  includeBlocks: {
    groupByCategory: false,
    generatedBlocks: {
      robot: ["forward", "left", "right", "obstacleInFront"],
    },
    standardBlocks: {
      includeAll: false,
      wholeCategories: [],
      singleBlocks: {
        easy: ["controls_if", "controls_repeat"],
        medium: [
          "logic_negate",
          "controls_if",
          "controls_if_else",
          "controls_repeat",
        ],
        hard: [
          "logic_negate",
          "controls_if",
          "controls_if_else",
          "controls_repeat",
        ],
      },
    },
    pythonAdditionalFunctions: {
      shared: ["range"],
    },
  },
  blocklyColourTheme: "bwinf",
  additionalBlocksByLevel: {
    easy: { generatedBlocks: {}, standardBlocks: {} },
    medium: { standardBlocks: {} },
  },
  ignoreInvalidMoves: false,
  checkEndEveryTurn: false,
  // checkEndCondition: function(context, lastTurn) {
  //    var solved = true;
  //    for (var iRow = 0; iRow < context.tiles.length; iRow++) {
  //       var row = subTask.data[subTask.level][subTask.iTestCase].tiles[iRow];
  //       for (var iCol = 0; iCol < row.length; iCol++) {
  //          var collectibles = context.getItems(iRow, iCol, {isCollectible: true});
  //          if (collectibles.length != 0) {
  //             solved = false;
  //          }
  //       }
  //    }
  //    if (solved) {
  //       context.success = true;
  //       throw(window.taskStrings.success);
  //    }
  //    if (lastTurn) {
  //       context.success = false;
  //       throw(window.taskStrings.failure);
  //    }
  // },
  // computeGrade: function(context, message) {
  //    var rate = 0;
  //    if (context.success) {
  //       rate = 1;
  //    }
  //    return {
  //       successRate: rate,
  //       message: message
  //    };
  // }
};

subTask.data = {
  easy: [
    {
      tiles: [
        [1, 1, 1, 1, 1, 4, 1],
        [1, 1, 4, 1, 1, 3, 1],
        [1, 1, 4, 3, 4, 1, 1],
        [4, 4, 4, 4, 4, 1, 4],
        [1, 1, 1, 1, 1, 3, 1],
        [1, 3, 1, 3, 1, 1, 4],
        [1, 1, 1, 1, 1, 1, 1],
      ],
      initItems: [{ row: 5, col: 0, dir: 0, type: "robot" }],
    },
  ],
  medium: [
    {
      tiles: [
        [1, 1, 1, 4, 1, 1, 1, 4, 1],
        [1, 1, 4, 1, 1, 3, 1, 3, 4],
        [1, 1, 1, 3, 1, 1, 4, 1, 1],
        [1, 1, 1, 1, 1, 4, 3, 1, 4],
        [1, 1, 1, 1, 1, 1, 1, 4, 1],
        [1, 1, 1, 1, 4, 4, 3, 1, 1],
        [4, 4, 4, 1, 3, 4, 1, 1, 1],
        [1, 1, 1, 4, 4, 4, 1, 1, 1],
        [1, 1, 1, 3, 1, 1, 3, 4, 1],
        [1, 1, 1, 1, 1, 1, 4, 1, 1],
      ],
      initItems: [{ row: 8, col: 0, dir: 0, type: "robot" }],
    },
  ],
  hard: [
    {
      tiles: [
        [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
        [1, 1, 1, 1, 1, 4, 3, 1, 4, 1, 1, 1],
        [1, 1, 1, 3, 4, 4, 4, 4, 4, 4, 3, 1],
        [1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 1],
        [1, 1, 3, 4, 4, 4, 4, 4, 4, 4, 4, 1],
        [1, 1, 4, 4, 4, 4, 4, 4, 4, 3, 1, 1],
        [1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1],
        [1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 1, 1],
        [1, 1, 1, 3, 1, 4, 4, 4, 4, 3, 1, 1],
        [1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1],
      ],
      initItems: [{ row: 6, col: 1, dir: 3, type: "robot" }],
    },
  ],
};
