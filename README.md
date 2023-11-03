# engine-alpha-jwinf

Implementation der Übungsaufgaben auf jwinf in Java mit Hilfe der engine alpha

# Build dependency

make dependency

https://git.bwinf.de/bwinf/medal

https://github.com/France-ioi/bebras-modules

https://www.france-ioi.org/

https://github.com/France-ioi/bebras-modules/blob/master/pemFioi/quickAlgo/README.md

https://jwinf.de/tasks/jwinf/jwinf-aufgaben/2020/20-DE-13-Kerzen-einfach/task_new.js

https://concours.castor-informatique.fr/

```js
function initTask(subTask) {
  var cellSide = 60;

  subTask.gridInfos = {
    hideSaveOrLoad: true,
    conceptViewer: false,
    contextType: "paint",
    //cellSide: cellSide,
    actionDelay: 200,
    languageStrings: {
      blocklyRobot_lib: {
         label: {
            "onPaint": "auf Kerze",
            "dropObject" : "zünde Kerze an"
         },
         code: {
          onPaint: "aufKerze",
          dropObject : "zuendeKerzeAn"
         },
         messages: {
            successContainersFilled: "Bravo, der Roboter hat alle Kerzen angezündet!",
            failureContainersFilled: "Der Roboter hat die Kerzen nicht korrekt angezündet.",
         }
      }
   },
    itemTypes: {
      robot: {img: imgPath+"blue_robot.png", side: 85, isRobot: true, offsetX: -10, offsetY: 10, zOrder: 1},
      initial_paint: {num: 3, img: "kerze.png", side: 60, isPaint: true, zOrder: 1},
      paint: {num: 2, img: "flamme.png", side: 60,  isWithdrawable: true, zOrder: 1},
      marker: {num: 4, img: "docht.png", side: 60, isContainer: true, containerFilter: function(item) {return item.type === "paint";}, zOrder: 0},
      number: { side: 60, zOrder: 2 },
      board_background: { num: 5, color: "#ffffff", side: 60, zOrder: 0 },
      board: { side: 60, isWritable: true, zOrder: 1 }
    },
    maxInstructions: {
      easy: 10,
      medium: 10,
      hard: 10,
    },
    includeBlocks: {
      groupByCategory: false,
      generatedBlocks: {
        robot: {
          shared: ["east", "west", "north", "south", "dropObject"],
          easy: [],
          medium: [],
          hard: ["onPaint"]
        }
      },
      standardBlocks: {
        includeAll: false,
        wholeCategories: {
          easy: [],
          medium: [],
          hard: []
        },
        singleBlocks: {
          shared: ["controls_repeat"],
          easy: [],
          medium: [],
          hard: ["controls_if"] // ["logic_negate", "controls_if_else"]
        }
      },
      pythonAdditionalFunctions: {
         shared: ["range"]
      },
    },

    blocklyColourTheme: "bwinf",
    ignoreInvalidMoves: false,
    checkEndEveryTurn: false,
  };

  subTask.data = {
    easy: [{
      tiles: [
        [1, 1, 1, 1, 1, 1, 1, 1, 1],
        [1, 1, 1, 1, 1, 1, 1, 1, 1],
        [1, 1, 1, 1, 4, 1, 1, 1, 1],
        [1, 1, 1, 1, 3, 1, 1, 1, 1],
        [1, 1, 1, 1, 3, 1, 1, 1, 1],
        [1, 1, 1, 1, 3, 1, 1, 1, 1]
      ],
      initItems: [{
        row: 5,
        col: 1,
        type: "robot"
      }, ]
    }],
    medium: [{
      tiles: [
        [1, 1, 1, 1, 1, 1, 1, 1, 1],
        [1, 1, 4, 4, 4, 4, 4, 1, 1],
        [1, 1, 3, 3, 3, 3, 3, 1, 1],
        [1, 1, 3, 3, 3, 3, 3, 1, 1],
        [1, 1, 3, 3, 3, 3, 3, 1, 1],
        [1, 1, 3, 3, 3, 3, 3, 1, 1]
      ],
      initItems: [{
        row: 5,
        col: 1,
        type: "robot"
      }, ]
    }],
    hard: [{
      tiles: [
      [1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
      [1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
      [1, 1, 4, 1, 4, 4, 1, 1, 4, 1],
      [1, 1, 3, 1, 3, 3, 1, 1, 3, 1],
      [1, 1, 3, 1, 3, 3, 1, 1, 3, 1],
      [1, 1, 3, 1, 3, 3, 1, 1, 3, 1]
      ],
      initItems: [{
        row: 5,
        col: 1,
        type: "robot"
      }, ]
    }]
  };

  initBlocklySubTask(subTask);
  displayHelper.thresholdEasy = 120;
  displayHelper.thresholdMedium = 240;
}

initWrapper(initTask, ["easy", "medium", "hard"], null, true);
```
