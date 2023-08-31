import React from 'react'
import {ComponentPreview, Previews} from '@react-buddy/ide-toolbox'
import {PaletteTree} from './palette'
import {Details} from "../pages/Details/index.jsx";

const ComponentPreviews = () => {
  return (
    <Previews palette={<PaletteTree/>}>
      <ComponentPreview path="/Details">
        <Details/>
      </ComponentPreview>
    </Previews>
  )
}

export default ComponentPreviews