const subTask = {};

var initArray = function (n, elem) {
  var res = [];
  for (var i = 0; i < n; i++) {
    res.push(elem);
  }
  return res;
};

export default subTask;
   subTask.gridInfos = {
      contextType: "sokoban",
      conceptViewer: false,
      blocklyColourTheme: "bwinf",
		maxInstructions: {
            easy: 20,
            medium: 25,
            hard: 40
         },
      includeBlocks: {
         groupByCategory: false,
         generatedBlocks: {
            robot: {
					shared: ["left", "right", "forward", "pushObject", "pushableInFront"],
					hard: ["obstacleInFront"]
				}
         },
         standardBlocks: {
            includeAll: false,
            wholeCategories: [],
            singleBlocks: {shared: ["controls_repeat", "controls_if", "controls_if_else"], hard: ["logic_negate"]}
         }
      }
   };

   subTask.data = {
      easy: [
         {
            tiles: [
                   [2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2],
                   [2, 1, 1, 3, 1, 3, 3, 1, 1, 3, 1, 3, 2],
                   [2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2],
						 [2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2],
						 [2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2]
               ],
            initItems: [
                  { row: 3, col: 1, dir: 0, type: "robot" },
                  { row: 2, col: 3, type: "box" },
						{ row: 2, col: 5, type: "box" },
						{ row: 2, col: 6, type: "box" },
						{ row: 2, col: 9, type: "box" },
						{ row: 2, col: 11, type: "box" }
               ]
         }
      ],
      medium: [
         {
            tiles: [
                   [2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2],
                   [2, 1, 3, 3, 1, 1, 3, 1, 3, 3, 1, 3, 2],
                   [2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2],
						 [2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2],
						 [2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2],
						 [2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2],
						 [2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2],
						 [2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2],
						 [2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2],
						 [2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2],
						 [2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2]
               ],
            initItems: [
                  { row: 9, col: 1, dir: 0, type: "robot" },
                  { row: 4, col: 2, type: "box" },
						{ row: 8, col: 3, type: "box" },
						{ row: 6, col: 6, type: "box" },
						{ row: 2, col: 8, type: "box" },
						{ row: 5, col: 9, type: "box" },
						{ row: 7, col: 11, type: "box" }
					]
         }
      ],
      hard: [
         {
            tiles: [
                   [2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2],
                   [2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2],
						 [2, 1, 1, 2, 2, 2, 1, 1, 1, 1, 3, 1, 2],
                   [2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2],
						 [2, 1, 1, 1, 3, 1, 1, 1, 1, 3, 1, 1, 2],
						 [2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2],
						 [2, 1, 2, 2, 2, 1, 1, 1, 3, 1, 1, 1, 2],
						 [2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2],
						 [2, 1, 1, 2, 2, 2, 1, 1, 2, 2, 2, 1, 2],
						 [2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2],
						 [2, 1, 1, 1, 3, 1, 1, 1, 2, 2, 2, 1, 2],
						 [2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2],
						 [2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2]
               ],
            initItems: [
                  { row: 11, col: 1, dir: 3, type: "robot" },
                  { row: 2, col: 8, type: "box" },
						{ row: 4, col: 2, type: "box" },
						{ row: 4, col: 7, type: "box" },
						{ row: 6, col: 6, type: "box" },
						{ row: 10, col: 2, type: "box" }
					]
         }
      ]
   };
