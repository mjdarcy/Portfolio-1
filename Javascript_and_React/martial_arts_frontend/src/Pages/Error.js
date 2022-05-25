import React from 'react';
import NavBar from '../Components/NavBar';

export default class Error extends React.Component
{
  constructor(props)
  {
    super(props);
    this.state = 
    {
        style:
        {
            borderStyle: "solid"
        }
    }
}

    render()
    {
        return(
            <div style={this.state.style}>
                <NavBar/>
                <p>Error</p>
            </div>
        )
    }
}