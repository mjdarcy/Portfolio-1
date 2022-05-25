import React from 'react';

export default class TechVideo extends React.Component
{
    constructor(props)
    {
        super(props);
        this.state = 
        {
            style:
            {
                borderStyle: "solid",
                height: 500,
                width: 500,
                float: "left"
            }
        }
    }

    render()
    {
        return(
            <div style={this.state.style}>
              <video style={{display: "block", margin:"auto", height:500, width: 500}}controls src={this.props.videoUrl}></video>
            </div>
        )
    }
}