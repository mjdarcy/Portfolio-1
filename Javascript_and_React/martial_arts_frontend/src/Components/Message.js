import React from 'react';

export default class Message extends React.Component
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
            hi
        </div>)
    }
}