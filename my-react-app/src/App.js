// App.js
import './App.css';
import Navbar from './Navbardoctor';
import Home from './Home';
// import Footer from './Footer';
import SideNav from './SideNav';
import { Route, Routes, useLocation } from 'react-router-dom';
import RoomPage from './screens/Room';
import LobbyScreen from './screens/Lobby';
import Patientscreen from './screens/Patient';
import DoctorLogin from './Doctorlogin'; // Import DoctorLogin component
import Appointment from './Appointment';
import Prescription from './Prescription';
import IncomingCall from './screens/Incomincall';

function App() {
  const location = useLocation();

  return (
    <div className="App">
      
      {location.pathname !== "/patient" && location.pathname !== "/login" && <Navbar /> }
      <div style={{ display: 'flex' }}>
        {location.pathname !== "/patient" && location.pathname !== "/login" && <SideNav />}
        
        <Routes>
          <Route path="/" element={<Home/>}/>
          <Route path="/call" element={<LobbyScreen />} />
          <Route path="/appointments" element={<Appointment/>} />
          <Route path="/prescription" element={<Prescription/>} />
          <Route path="/room/:roomId" element={<RoomPage />} />
          <Route path="/patient" element={<Patientscreen/>} />
          <Route path="/incomingcall" element={<IncomingCall/>}></Route>
          <Route path="/login" element={<DoctorLogin />} /> {/* Render DoctorLogin component inside a route */}
        </Routes>
      </div>
    </div>
  );
}

export default App;
