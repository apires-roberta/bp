import { Upload } from "@aws-sdk/lib-storage";
import { S3 } from "@aws-sdk/client-s3";

let teste = 'https://s3-lab04-juan.s3.amazonaws.com/';
function UploadImageToS3WithNativeSdk(){
function upload (){
    var fileInput = document.getElementById("teste");
    var file = fileInput.files[0];

    const target = {Bucket:"s3-lab04-juan", Key:file.name, Body:file}//s3-project-bp
    const creds = {accessKeyId:"ASIAYMJR7UFMAMZH44W7", secretAccessKey:"kovRM6lMCcx8++tzim7BhqE1ezcr0PQhcqRlz0hz", sessionToken:"FwoGZXIvYXdzEHAaDICAZ+vSN//BbGgneyLCAW5ENkoWGXvVtZW5rGqveWjXpfduZx0Y56bi8XWjOTE+qwB6lAZO51Sc6iMWobKChGul0G1Z/jQ/WH+xLrZcVG7PnMW0dw6t4Wq3zafKS2nsV71y8/lr6WYKztvS7gK0a/AQu1PK3K/aLaaxSe8yE9U0WIVLsQjyK3jEuLKepOYY9VJ9q93SL6c5iQ6XUaUG+F/mq+uPtd0aefMsf9MdBdAstD717R0wmO3SvZcp2wKpjczlHijV1trSG5HunehXL+ouKOPdvJoGMi1/R0H4CEzzV4PmHxgFF/bkaMYcDGlwjlZlRqDdPhvWs9jWeHqU5UxDevwbsTA="}
    console.log(fileInput.files[0].name)
    try {
      const parallelUploads3 = new Upload({
        client: new S3({region:"us-east-1", credentials:creds}),
        leavePartsOnError: false, // optional manually handle dropped parts
        params: target,
      });
    
      parallelUploads3.on("httpUploadProgress", (progress) => {
        console.log(progress);
      });
    
      parallelUploads3.done();
    } catch (e) {
      console.log(e);
    }
}

    return (<div>
        <div>Native SDK File Upload Progress is</div>
        <input type="file" id="teste"/>
        <button onClick={() => upload()}> Upload to S3</button>
    </div>)
}

export default UploadImageToS3WithNativeSdk;