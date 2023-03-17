import React from 'react'
import { useState } from 'react';
import './FilterForm.css'

function FilterForm() {

    const [data, setData] = useState([])
    const [showTable, setShowTable] = useState([])

    const fetchData = () => {
        fetch('http://localhost:8080/review/sortedByDate')
          .then(response => response.json())
          .then(data => {
            setData(data)
            setShowTable(true)
          })
          .catch(error => console.error(error))
          console.log(data)
      }
    
  return (
    <div>
        <form className='form-style'>
            <h4>Filter reviews</h4>
            <label for="formFilter" class="form-label">Order by rating:</label>
            <select className="form-select">
                <option selected>Highest first</option>
                <option value="1">Lowest first</option>
            </select>
            <br />
            <label for="formFilter" class="form-label">Minimum rating:</label>
            <select className="form-select">
                <option selected>5</option>
                <option value="1">4</option>
                <option value="2">3</option>
                <option value="3">2</option>
                <option value="3">1</option>
            </select>
            <br />
            <label for="formFilter" class="form-label">Order by date:</label>
            <select className="form-select">
                <option selected>Oldest first</option>
                <option value="1">Newest first</option>
            </select>
            <br />
            <label for="formFilter" class="form-label">Prioritize by text:</label>
            <select className="form-select">
                <option selected>Yes</option>
                <option value="1">No</option>
            </select>
            <br />
            <button type="button" class="btn btn-primary" onClick={fetchData}>Filter</button>
            {showTable && (
                <table className='table'>
                <thead>
                  <tr>
                    <th>ReviewText</th>
                    <th>Date</th>
                  </tr>
                </thead>
                <tbody>
                  {data.map(item => (
                    <tr key={item.id}>
                      <td>{item.reviewText}</td>
                      <td>{item.reviewCreatedOnDate}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
            )}   
        </form>
    </div>
  )
}

export default FilterForm
