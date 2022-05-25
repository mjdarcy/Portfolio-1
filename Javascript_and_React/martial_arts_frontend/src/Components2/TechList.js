import React from 'react';

export default class TechniqueList extends React.Component
{
    constructor(props)
    {
        super(props);
        this.state = 
        {
            types: [],
            techSelected: null,
            style:
            {
                borderStyle: "solid",
                height: 800,
                width: 400,
                float: "left"
            }
        }
    }

    componentDidMount()
    {
      this.loadData();
    }

    /*
    loadData()
    {
      fetch(this.props.apiUrl + "/technique/types")
        .then(response => response.json())
        .then(data => this.setState({types: data}));
    }
    */
   
    typeList()
    {
      let types = this.state.types
      let result = types.map( (e)=><Type key={e} apiUrl={this.props.apiUrl} type={e} setSelectedTechnique={this.props.setSelectedTechnique}/> );
      return result;
    }

    render()
    {
        return <div style={this.state.style}>{this.typeList()}</div>
    }
}

class Type extends React.Component
{
    constructor(props)
    {
        super(props);
        this.state = 
        {
          selected: false,
          techniques: null,
          style:
          {
            borderStyle: "solid",
            width: 394,
            height: 100,
            backgroundColor: "#6495ED"
          }
        }
        this.onClickHandler = this.onClickHandler.bind(this);
        this.deleteSelectedTechnique = this.deleteSelectedTechnique.bind(this);
    }

    deleteSelectedTechnique(name, id)
    {
      let check = window.confirm("Are you sure you would like to delete " + name);
      if(check)
      {
        fetch(this.props.apiUrl + "/technique/delete/" + id, {method: "delete"})
        .catch((e) => console.log(e));

        let newTechs = [];
        for(const e of this.state.techniques) if(e.id != id) newTechs.push(e);
        this.setState({ techniques: newTechs });
      }
    }

    componentDidMount()
    {
      this.loadData();
    }

    loadData()
    {
      fetch(this.props.apiUrl + "/technique/type/" + this.props.type)
        .then(response => response.json())
        .then(data => this.setState({techniques: data}));
    }

    onClickHandler()
    {
      this.setState({selected: !this.state.selected})
      console.log(this.state.selected);
    }

    techList()
    {
      let data = this.state.techniques;
      let result = data.map((e)=><Technique key={e.id} data={e} setSelectedTechnique={this.props.setSelectedTechnique} deleteSelectedTechnique={this.deleteSelectedTechnique} apiUrl={this.props.apiUrl}/>)
      return result;
    }

    render()
    {
      if(!this.state.selected) return <div style={this.state.style} onClick={this.onClickHandler}>{this.props.type}</div>
      else return(
        <div>
          <div style={this.state.style} onClick={this.onClickHandler}>{this.props.type}</div>
          {this.techList()}
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
          selected: false
        }

        this.clickHandler = this.clickHandler.bind(this);
        this.deleteHandler = this.deleteHandler.bind(this);
    }

    clickHandler()
    {
      this.props.setSelectedTechnique(this.props.data)
    }

    deleteHandler()
    {
      this.props.deleteSelectedTechnique(this.props.data.name, this.props.data.id);
    }

    render()
    {
      const style =
      { 
       borderStyle: "solid",
       backgroundColor: "#00FFFF"
      }

        return(
            <div style={style} onClick={this.clickHandler}>
              {this.props.data.name}
              <div style={{backgroundColor: "red", width: 50, height: 50}} onClick={this.deleteHandler}>Delete</div>
            </div>
        )
    }
}