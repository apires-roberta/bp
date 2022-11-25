import React from "react";
import { geoCentroid } from "d3-geo";
import {
  ComposableMap,
  Geographies,
  Geography,
  Marker,
  Annotation
} from "react-simple-maps";
import ColetarDados from "./GraficoEstado";
import Box from '@mui/material/Box';
import Modal from '@mui/material/Modal';


import brTopoJson from "../data/br-topo.json";
const statesWithAnnotations = {
  BR_DF: {
    annotation: { x: -10, y: -15 },
    tag: { fontSize: 14, x: 4, y: 0 }
  },
  BR_RN: {
    annotation: { x: 28, y: 0 },
    tag: { fontSize: 14, x: 4, y: 0 }
  },
  BR_PB: {
    annotation: { x: 32, y: 0 },
    tag: { fontSize: 14, x: 4, y: 0 }
  },
  BR_PE: {
    annotation: { x: 50, y: 0 },
    tag: { fontSize: 14, x: 4, y: 0 }
  },
  BR_AL: {
    annotation: { x: 30, y: 0 },
    tag: { fontSize: 14, x: 4, y: 0 }
  },
  BR_SE: {
    annotation: { x: 25, y: 0 },
    tag: { fontSize: 14, x: 4, y: 0 }
  },
  BR_ES: {
    annotation: { x: 20, y: 0 },
    tag: { fontSize: 14, x: 4, y: 0 }
  },
  BR_RJ: {
    annotation: { x: 25, y: 0 },
    tag: { fontSize: 14, x: 4, y: 0 }
  },
  
};

const geographyStyle = {
  stroke: "#607D8B",
  strokeWidth: 1,
  outline: "none",
  cursor: "pointer",
  transition: "all .2s"
};

function MapChart(props) {
  const renderGeograph = (dataSource, countryId) => {
    return (
      <Geographies geography={dataSource}>
        {({ geographies }) => (
          <>
            {geographies.map((geo) => {
              console.log(geo);
              return (
                <Geography
                  key={geo.rsmKey + "-Geography"}
                  geography={geo}
                  onClick={()=>abrirModal(geo.properties.id)}
                  style={{
                    default: {
                      ...geographyStyle,
                      fill: props.fundo
                    },
                    hover: {
                      ...geographyStyle,
                      fill: props.hover
                    },
                    pressed: {
                      ...geographyStyle,
                      fill: props.click
                    }
                  }}
                />
              );
            })}

            {geographies.map((geo) => {
              const centroid = geoCentroid(geo);
              const geoId = geo.properties.id;
              const annotationOffset =
                statesWithAnnotations[`${countryId}_${geoId}`];
              const tagPosition = annotationOffset?.tag || {
                x: 0,
                y: 0,
                fontSize: 14
              };
              return (
                <g
                  key={`${geo.rsmKey}-Marker`}
                  style={{ pointerEvents: "none" }}
                >
                  {annotationOffset ? (
                    <Annotation
                      connectorProps={{
                        stroke: props.linha
                      }}
                      subject={centroid}
                      dx={annotationOffset.annotation.x}
                      dy={annotationOffset.annotation.y}
                    >
                      <text
                        x={tagPosition.x}
                        y={tagPosition.y}
                        fontSize={tagPosition.fontSize}
                        alignmentBaseline="middle"
                        fill={props.linha}
                      >
                        {geoId}
                      </text>
                    </Annotation>
                  ) : (
                    <Marker coordinates={centroid}>
                      <text
                        x={tagPosition.x}
                        y={tagPosition.y}
                        fontSize={tagPosition.fontSize}
                        textAnchor="middle"
                        fill={props.linha}
                      >
                        {geoId}
                      </text>
                    </Marker>
                  )}
                </g>
              );
            })}
          </>
        )}
      </Geographies>
    );
  };
  const [open, setOpen] = React.useState(false);
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);
    const style = {
        position: 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: '80%',
        border: 'none',
        boxShadow: 24,
        borderRadius: "20px",
        height: '80%',
        backgroundColor: 'white',
        overflowY: 'scroll',
    };

    function abrirModal(estado){
      sessionStorage.setItem("estado", estado)
      handleOpen()
    }
  return (
    <>
      <ComposableMap
        projection="geoMercator"
        projectionConfig={{
          scale: 800,
          center: [-50, -15]
        }}
      >
        {renderGeograph(brTopoJson, "BR")}
        </ComposableMap>
        <Modal open={open} onClose={handleClose}>
          <Box sx={style} className="caixa">
            <ColetarDados />
          </Box>
        </Modal>
        </>
  );
};

export default MapChart;
