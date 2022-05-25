import React from 'react';

export default class TechDescription extends React.Component
{
    constructor(props)
    {
        super(props);
        this.state = 
        {
            style:
            {
                borderStyle: "solid",
                backgroundColor: "red"
            }
        }
    }

    render()
    {
        return(
            <div style={this.state.style}>
                <p style={this.state.style}>{this.props.desc}</p>
            </div>
        )
    }
}