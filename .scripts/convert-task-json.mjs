#! /usr/bin/env node

import * as fs from 'node:fs'
import * as path from 'node:path'

const dataDir = './.scripts/data'

function convertToJson(baseName, input) {
  const result = {
    gridInfos: input.gridInfos,
    levels: input.data
  }

  const json = JSON.stringify(input, null, 2)
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
