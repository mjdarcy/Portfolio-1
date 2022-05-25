import React from 'react';

export default class BlogPost extends React.Component
{
  constructor(props)
  {
    super(props);
    this.state = 
    {
        style:
        {
            borderStyle: "solid",
            height: 100
        }
    }
}

    render()
    {
        return(
        <div style={this.state.style}>
            {this.props.date} <br/>
            {this.props.content}
        </div>)
    }
}