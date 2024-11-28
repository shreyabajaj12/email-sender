import { useState } from 'react'
import './App.css'
import EmailSender from "./Components/EmailSender.jsx";
import {Toaster} from "react-hot-toast";

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <EmailSender/>
        <Toaster/>
    </>
  )
}

export default App
