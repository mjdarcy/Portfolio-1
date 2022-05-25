import React from 'react';

export default class TechList extends React.Component
{
  constructor(props)
  {
    super(props);
    this.state = 
    {
        techniques:[],
        style:
        {
            borderStyle: "solid"
        }
    }
}

    componentDidMount()
    {
        let techs = this.props.getTechniques("aj");
        this.setState({techniques: techs});
        console.log(techs)
    }

    typeList()
    {
        
        return this.state.techniques.map(e => <Type id={e[0].id} techs={e} setSelectedTechnique={this.props.setSelectedTechnique}/>)
    }

    render()
    {
        return(
        <div style={this.state.style}>

        </div>)
    }
}

class Type extends React.Component
{
        constructor(props)
        {
        super(props);
        this.state = 
        {
            toggle: false,
            techniques: [],
            style:
            {
                borderStyle: "solid",
                height: 100
            }
        }
    }

    componentDidMount()
    {
        this.state.techniques = this.props.techniques;
        console.log(this.state.techniques);
    }

    techniqueList()
    {
        return this.state.techniques.map(e => <Technique id={e.id} technique={e} setSelectedTechnique={this.props.setSelectedTechnique}/>)
    }

    toggleSelect(){ this.setState({toggle: !this.state.toggle}) }

    render()
    {

        return(
            <div style={this.state.style}>
                <div onClick={this.toggleSelect()}>{this.props.techs[0].type}</div>
            </div>
        )
    }
}


class Technique extends React.Component
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
              <div style={this.state.style} onClick={this.props.setSelectedTechnique(this.props.technique)}>
              </div>
          )
      }
}

