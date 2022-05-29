import React from 'react';
import NavBar from '../Components/NavBar';

export default class Error extends React.Component
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
                <p>Error</p>
            </div>
        )
    }
}