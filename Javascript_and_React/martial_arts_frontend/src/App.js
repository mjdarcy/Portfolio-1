import React from 'react';
import Register from './Pages/Register';
import Forum from './Pages/Forum';
import Library from './Pages/Library';
import About from './Pages/About';
import DevBlog from './Pages/DevBlog';
import Feedback from './Pages/Feedback';
import Login from './Pages/Login';
import Error from './Pages/Error';

export default class App extends React.Component
{
  constructor(props)
  {
    super(props);
    this.state = 
    {
      apiUrl: "http://localhost:8080/api",
      currPath: window.location.pathname
    }
  }

  render()
  {
    if(this.state.currPath === "/" || this.state.currPath === "/devblog") return <DevBlog apiUrl={this.state.apiUrl}/>
    else if(this.state.currPath === "/library") return <Library apiUrl={this.state.apiUrl}/>
    else if(this.state.currPath === "/forum") return <Forum apiUrl={this.state.apiUrl}/>
    else if(this.state.currPath === "/about") return <About apiUrl={this.state.apiUrl}/>
    else if(this.state.currPath === "/feedback") return <Feedback apiUrl={this.state.apiUrl}/>
    else if(this.state.currPath === "/login") return <Login apiUrl={this.state.apiUrl}/>
    else if(this.state.currPath === "/register") return <Register apiUrl={this.state.apiUrl}/>
    else return <Error/>;
  };
}