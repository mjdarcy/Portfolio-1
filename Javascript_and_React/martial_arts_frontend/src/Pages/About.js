import React from 'react';
import NavBar from '../Components/NavBar';

export default class About extends React.Component
{
  constructor(props)
  {
    super(props);
    this.state = 
    {
    }
}

    render()
    {
        return(
        <div>
            <NavBar/>
            <p>About</p>
        </div>)
    }
}