#! /usr/bin/env node

import * as fs from 'node:fs'
import * as path from 'node:path'

const dataDir = './.scripts/data'

function convertToJson(baseName, input) {
  const result = {
    grid: input.gridInfos,
    levels: input.data
  }

  if (result.grid.itemTypes != null) {
    result.grid.tiles = result.grid.itemTypes
    delete result.grid.itemTypes
  }

  if (result.grid.borderColor != null) {
    result.grid.gridColor = result.grid.borderColor
    delete result.grid.borderColor
  }
  const json = JSON.stringify(result, null, 2)
  fs.writeFileSync(path.join(dataDir, baseName + '.json'), json)
}

async function convertFiles() {
  for (const file of fs.readdirSync(dataDir)) {
    if (file.endsWith('.mjs')) {
      const baseName = file.replace('.mjs', '')
      const input = (await import('./data/' + file)).default
      convertToJson(baseName, input)
    }
  }
}

await convertFiles()
