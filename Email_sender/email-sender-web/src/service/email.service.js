import {customAxios} from "./helper.js";

export async function sendEmail(emailData){
    const result =(await customAxios.post('/email/send',emailData)).data;
    return result;
}
