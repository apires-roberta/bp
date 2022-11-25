import { useEffect } from "react"
import apiAnalytics from "../apiAnalytics"

function UploadImageToS3WithNativeSdk(){
  useEffect(() => {
    const interval = setInterval(()=>{
    apiAnalytics.get('').then((resposta) => {
          console.log(resposta.data)
  })
    return clearInterval(interval)
},200)
    
},[])
}

export default UploadImageToS3WithNativeSdk;